///*
// * Copyright 2021 zjay(darzjay@gmail.com)
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *    http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package io.github.zjay.plugin.fastrequest.contributor;
//
//import com.intellij.ide.actions.searcheverywhere.FoundItemDescriptor;
//import com.intellij.ide.util.gotoByName.*;
//import com.intellij.openapi.progress.ProgressIndicator;
//import com.intellij.openapi.progress.ProgressManager;
//import com.intellij.openapi.project.Project;
//import com.intellij.psi.PsiElement;
//import com.intellij.psi.codeStyle.NameUtil;
//import com.intellij.psi.search.GlobalSearchScope;
//import com.intellij.util.CollectConsumer;
//import com.intellij.util.Processor;
//import com.intellij.util.SynchronizedCollectConsumer;
//import com.intellij.util.containers.ContainerUtil;
//import com.intellij.util.indexing.FindSymbolParameters;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.*;
//
//public class RequestMappingItemProvider extends DefaultChooseByNameItemProvider  {
//    private RequestMappingModel model;
//
//    public RequestMappingItemProvider(@Nullable PsiElement context, RequestMappingModel model) {
//        super(context);
//        this.model = model;
//    }
//
//    @Override
//    public boolean filterElementsWithWeights(@NotNull ChooseByNameViewModel base,
//                                             @NotNull FindSymbolParameters parameters,
//                                             @NotNull ProgressIndicator indicator,
//                                             @NotNull Processor<? super FoundItemDescriptor<?>> consumer) {
//        return ProgressManager.getInstance().computePrioritized(() -> doFilterElements(base, parameters, indicator, consumer));
//    }
//
//    public boolean doFilterElements(@NotNull ChooseByNameViewModel base, FindSymbolParameters parameters, @NotNull ProgressIndicator indicator, @NotNull Processor<Object> consumer) {
//        Project project = base.getProject();
//        if(project == null){
//            return false;
//        }
//        GlobalSearchScope searchScope = FindSymbolParameters.searchScopeFor(project, everywhere);
//        List<String> namesList = getSortedResults(base, pattern, indicator, parameters);
//        indicator.checkCanceled();
//        return processByNames(base, everywhere, indicator, consumer, namesList, parameters);
//    }
//
//    private List<String> getSortedResults(
//            ChooseByNameViewModel base,
//             String pattern,
//            ProgressIndicator indicator,
//            FindSymbolParameters parameters
//    ) {
////        if (pattern.isEmpty() && !base.canShowListForEmptyPattern()) {
////            return new ArrayList<>();
////        }
//
//        List<String> namesList = new ArrayList<>();
//        CollectConsumer<String> collect = new SynchronizedCollectConsumer<>(namesList);
////        ChooseByNameModel model = base.getModel();
//        if (model != null) {
//            indicator.checkCanceled();
//            ((ChooseByNameModelEx) model).processNames(sequence -> {
//                indicator.checkCanceled();
//                if (matches(sequence, pattern) ) {
//                    collect.consume(sequence);
//                    return true;
//                }
//                return false;
//            },parameters);
//        }
//        indicator.checkCanceled();
//        return namesList;
//    }
//
//    private boolean processByNames(
//            ChooseByNameViewModel base,
//            Boolean everywhere,
//            ProgressIndicator indicator,
//            Processor<Object> consumer,
//            List<String> namesList,
//            FindSymbolParameters parameters
//    ) {
//
//        List<Object> sameNameElements = new ArrayList<>();
//        Map<Object, MatchResult> qualifierMatchResults = new HashMap<>();
//        ChooseByNameModel model = base.getModel();
//        for (String name : namesList) {
//            indicator.checkCanceled();
//            Object[] elements;
//            if(model instanceof ContributorsBasedGotoByModel){
//                elements = ((ContributorsBasedGotoByModel) model).getElementsByName(name, parameters, indicator);
//            } else {
//                elements = model.getElementsByName(name, everywhere, parameters.getCompletePattern());
//            }
//            if (elements.length > 1) {
//                sameNameElements.clear();
//                qualifierMatchResults.clear();
//                for (Object element : elements) {
//                    indicator.checkCanceled();
//                    sameNameElements.add(element);
//                }
//                if (!ContainerUtil.process(sameNameElements, consumer)) return false;
//            } else if (elements.length == 1) {
//                if (!consumer.process(elements[0])) return false;
//            }
//        }
//        return true;
//    }
//
//
//    private Boolean matches(String name, String pattern) {
//        if (name.isBlank()) {
//            return false;
//        }
//        try {
//            if (Objects.equals(pattern, "/")) {
//                return true;
//            } else {
//                String[] patternSplit = pattern.split(" ");
//                if(patternSplit.length == 1){
//                    return NameUtil.buildMatcher("*"+pattern, NameUtil.MatchingCaseSensitivity.NONE).matches(name);
//                } else {
//                    String[] nameSplit = name.split(" ");
//                    return
//                            NameUtil.buildMatcher("*"+patternSplit[0].toUpperCase(), NameUtil.MatchingCaseSensitivity.NONE).matches(nameSplit[0])
//                            && NameUtil.buildMatcher("*"+patternSplit[1].toUpperCase(), NameUtil.MatchingCaseSensitivity.NONE).matches(nameSplit[1])
//                            ;
//                }
//            }
//        } catch (Exception e) {
//            // no matches appears valid result for "bad" pattern
//            return false;
//        }
//    }
//
//
//}
