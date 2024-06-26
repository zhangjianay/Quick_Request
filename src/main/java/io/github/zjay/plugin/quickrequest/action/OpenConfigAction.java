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

package io.github.zjay.plugin.quickrequest.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import io.github.zjay.plugin.quickrequest.base.ParentAction;
import io.github.zjay.plugin.quickrequest.util.MyResourceBundleUtil;
import org.jetbrains.annotations.NotNull;

public class OpenConfigAction extends ParentAction {

    public OpenConfigAction() {
        super(MyResourceBundleUtil.getKey("ManageConfig"), MyResourceBundleUtil.getKey("ManageConfig"), AllIcons.General.Settings);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getData(LangDataKeys.PROJECT);
        ShowSettingsUtil.getInstance().showSettingsDialog(project, "Quick Request");
    }
}
