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

package io.github.zjay.plugin.quickrequest.contributor;

import com.intellij.navigation.ItemPresentation;
import com.intellij.pom.Navigatable;
import com.intellij.psi.*;
import com.intellij.psi.impl.FakePsiElement;
import io.github.zjay.plugin.quickrequest.util.FrIconUtil;
import io.github.zjay.plugin.quickrequest.util.ReflectUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Objects;


public  class RequestMappingItem extends FakePsiElement {
   private final Navigatable navigationElement;
   @NotNull
   private final PsiElement psiElement;
   private final String urlPath;
   private final String requestMethod;

   public String getRequestMethod(){
      return this.requestMethod;
   }
   @Override
   @NotNull
   public String getName() {
      return this.requestMethod + " " +this.urlPath;
   }

   @Override
   @NotNull
   public ItemPresentation getPresentation() {
      return new RequestMappingItem.RequestMappingItemPresentation();
   }

   @Override
   public void navigate(boolean requestFocus) {
      if (navigationElement != null) {
         navigationElement.navigate(requestFocus);
      }
   }



   @Override
   public boolean canNavigate() {
      return this.navigationElement != null;
   }

   @Override
   public boolean canNavigateToSource() {
      return true;
   }

   @Override
   public PsiElement getParent() {
      return psiElement.getParent();
   }

   @Override
   @NotNull
   public String toString() {
      return "RequestMappingItem(psiElement=" + this.psiElement + ", urlPath='" + this.urlPath + "', requestMethod='" + this.requestMethod + "', navigationElement=" + this.navigationElement + ')';
   }

   @NotNull
   public final PsiElement getPsiElement() {
      return this.psiElement;
   }

   public RequestMappingItem(@NotNull PsiElement psiElement, @NotNull String urlPath, @NotNull String requestMethod) {
      this.psiElement = psiElement;
      this.urlPath = urlPath;
      this.requestMethod = requestMethod;
      PsiElement var10001 = this.psiElement.getNavigationElement();
      if (!(var10001 instanceof Navigatable)) {
         var10001 = null;
      }

      this.navigationElement = (Navigatable)var10001;
   }

   public final class RequestMappingItemPresentation implements ItemPresentation {
      @Override
      @NotNull
      public String getPresentableText() {
         return RequestMappingItem.this.urlPath;
      }

      @Override
      @NotNull
      public String getLocationString() {
         String location = null;
         if (Objects.equals(psiElement.getClass().getCanonicalName(), "com.intellij.psi.impl.source.PsiMethodImpl")) {
            PsiMethod psiMethod = ((PsiMethod) psiElement);
            location = psiMethod.getContainingClass().getName().concat(".").concat(psiMethod.getName());
         }else {
            location = psiElement.getContainingFile().getName();
//            location = psiElement.getContainingFile().getVirtualFile().getPresentableUrl();
         }
         return "(" + location + ")";
      }

      @Override
      @NotNull
      public Icon getIcon(boolean b) {
         PsiElement psiElement = RequestMappingItem.this.getPsiElement();
         PsiFile psiFile = psiElement.getContainingFile();
         if(Objects.equals(psiElement.getClass().getCanonicalName(), "com.intellij.psi.impl.source.PsiJavaFileImpl")){
            PsiJavaFile psiJavaFile = (PsiJavaFile) psiFile;
            return FrIconUtil.getIconByMethodAndClassType(requestMethod, psiJavaFile.getClasses()[0].isInterface());
         }else {
            return FrIconUtil.getIconByMethodType(requestMethod);
         }
      }
   }

}
