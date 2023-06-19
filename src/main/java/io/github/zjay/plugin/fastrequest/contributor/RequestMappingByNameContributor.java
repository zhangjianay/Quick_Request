/*
 * Copyright 2021 zjay(darzjay@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.zjay.plugin.fastrequest.contributor;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiUtilCore;
import io.github.zjay.plugin.fastrequest.config.Constant;
import io.github.zjay.plugin.fastrequest.generator.FastUrlGenerator;
import io.github.zjay.plugin.fastrequest.generator.impl.DubboMethodGenerator;
import io.github.zjay.plugin.fastrequest.generator.impl.JaxRsGenerator;
import io.github.zjay.plugin.fastrequest.generator.impl.SpringMethodUrlGenerator;
import io.github.zjay.plugin.fastrequest.util.FrPsiUtil;
import io.github.zjay.plugin.fastrequest.view.linemarker.DubboLineMarkerProvider;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public abstract class RequestMappingByNameContributor implements ChooseByNameContributor {
    SpringMethodUrlGenerator springMethodUrlGenerator = ApplicationManager.getApplication().getService(SpringMethodUrlGenerator.class);
    JaxRsGenerator jaxRsGenerator = ApplicationManager.getApplication().getService(JaxRsGenerator.class);

    DubboMethodGenerator dubboMethodGenerator = ApplicationManager.getApplication().getService(DubboMethodGenerator.class);

    abstract List<PsiAnnotation> getAnnotationSearchers(String annotationName, Project project);
    private List<RequestMappingItem> navigationItems = new ArrayList<>();
    @Override
    public String @NotNull [] getNames(Project project, boolean includeNonProjectItems) {
        navigationItems = Constant.SUPPORTED_ANNOTATIONS.stream().flatMap(annotation -> findRequestMappingItems(project, annotation).stream()).collect(Collectors.toList());
        String[] strings = navigationItems.stream()
                .map(RequestMappingItem::getName).distinct().toArray(String[]::new);
        return strings;
    }




    @Override
    public NavigationItem @NotNull [] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
        RequestMappingItem[] requestMappingItems = navigationItems.stream().filter(q -> q.getName().equals(name)).toArray(RequestMappingItem[]::new);
        return requestMappingItems;
    }

    private List<RequestMappingItem> findRequestMappingItems(Project project, String annotationName) {
        List<PsiAnnotation> annotationSearchers = getAnnotationSearchers(annotationName, project);
        annotationSearchers = annotationSearchers.stream().filter(x->{
            PsiJavaCodeReferenceElement nameReferenceElement = x.getNameReferenceElement();
            if(nameReferenceElement != null){
                if(Objects.equals(nameReferenceElement.getText(), "Service")){
                    if(!Objects.equals(x.getQualifiedName(), Constant.DubboMethodConfig.AliService.getCode()) &&
                    !Objects.equals(x.getQualifiedName(), Constant.DubboMethodConfig.ApacheService.getCode())){
                        //Service的注解 必须要是dubbo的
                        return false;
                    }else {
                        return true;
                    }
                }
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        //restful request
        List<RequestMappingItem> restfulRequestList = annotationSearchers.stream().filter(q -> fetchAnnotatedPsiElement(q) instanceof PsiMethod)
                .map(annotation -> mapItems(annotation))
                .collect(Collectors.toList());

        List<RequestMappingItem> dubboRequestList = new LinkedList<>();
        //dubbo
        annotationSearchers.stream().filter(x-> Constant.DubboMethodConfig.exist(x.getQualifiedName()) && x.getParent().getParent() instanceof PsiClass
            ).forEach(psiAnnotation -> {
            PsiClass parent = (PsiClass) psiAnnotation.getParent().getParent();
            PsiMethod[] methods = parent.getMethods();
            for (PsiMethod method : methods) {
                if(!DubboLineMarkerProvider.judgeMethod(method)){
                    continue;
                }
                dubboRequestList.add(new RequestMappingItem(method,dubboMethodGenerator.getMethodRequestMappingUrl(method),"DUBBO"));
            }
        });
        dubboRequestList.addAll(restfulRequestList);
        return dubboRequestList;

    }



    private RequestMappingItem mapItems(PsiAnnotation psiAnnotation){
        PsiMethod method = (PsiMethod) fetchAnnotatedPsiElement(psiAnnotation);
        Constant.FrameworkType frameworkType = FrPsiUtil.calcFrameworkType(method);
        FastUrlGenerator generator;
        if (frameworkType.equals(Constant.FrameworkType.SPRING)) {
            generator = springMethodUrlGenerator;
        } else if(frameworkType.equals(Constant.FrameworkType.JAX_RS)){
            generator = jaxRsGenerator;
        }else {
            generator = dubboMethodGenerator;
            return new RequestMappingItem(method,generator.getMethodRequestMappingUrl(method),"DUBBO");
        }
        String methodUrl = generator.getMethodRequestMappingUrl(method);
        String classUrl = generator.getClassRequestMappingUrl(method);
        String originUrl = classUrl + "/" + methodUrl;
        originUrl = (originUrl.startsWith("/") ? "" : "/") + originUrl.replace("//", "/");
        String methodType = generator.getMethodType(method);
        return new RequestMappingItem(method,originUrl,methodType);
    }


    private PsiElement fetchAnnotatedPsiElement(PsiElement psiElement) {
        PsiElement parent = psiElement.getParent();
        parent = parent == null? PsiUtilCore.NULL_PSI_ELEMENT : parent;
        if (parent instanceof PsiMethod || parent instanceof PsiClass) return parent;
        return fetchAnnotatedPsiElement(parent);
    }
}
