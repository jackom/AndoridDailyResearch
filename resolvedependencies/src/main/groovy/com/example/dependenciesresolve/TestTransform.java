package com.example.dependenciesresolve;

import com.android.build.api.transform.Format;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.gradle.internal.pipeline.TransformManager;
import com.android.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @author : zhengminxin
 * @date : 10/14/2020 10:35 AM
 * @desc :
 */
class TestTransform extends Transform {

    @Override public String getName() {
        return "TestTransform";
    }

    @Override public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    @Override public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override public boolean isIncremental() {
        return false;
    }

    @Override public void transform(TransformInvocation invocation) {
        System.out.println("TestTransform transform");
        for (TransformInput input : invocation.getInputs()) {
            input.getJarInputs().parallelStream().forEach(jarInput -> {
                File src = jarInput.getFile();
                System.out.println("input.jarInputs fielName:" + src.getName());
                System.out.println("input.jarInputs getAbsolutePath:" + src.getAbsolutePath());
                File dst = invocation.getOutputProvider().getContentLocation(
                        jarInput.getName(), jarInput.getContentTypes(), jarInput.getScopes(),
                        Format.JAR);
                try {
                    FileUtils.copyFile(src, dst);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            input.getDirectoryInputs().parallelStream().forEach(directoryInput -> {
                File src = directoryInput.getFile();
                System.out.println("input.getDirectoryInputs fielName:" + src.getName());
                File dst = invocation.getOutputProvider().getContentLocation(
                        directoryInput.getName(), directoryInput.getContentTypes(),
                        directoryInput.getScopes(), Format.DIRECTORY);
                try {
                    scanFilesAndInsertCode(src.getAbsolutePath());
                    FileUtils.copyDirectory(src, dst);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
        }
    }

    private void scanFilesAndInsertCode(String path) throws Exception {
        ClassPool classPool = ClassPool.getDefault();
        classPool.appendClassPath(path);
        CtClass ctClass = classPool.getCtClass("com.example.testplugin.PluginTestClass");
        if (ctClass == null) {
            return;
        }
        if (ctClass.isFrozen()) {
            ctClass.defrost();
        }
        CtMethod ctMethod = ctClass.getDeclaredMethod("init");

        String insetStr = "System.out.println(\"insert codes\");";
        ctMethod.insertAfter(insetStr);
        ctClass.writeFile(path);
        ctClass.detach();
    }

}
