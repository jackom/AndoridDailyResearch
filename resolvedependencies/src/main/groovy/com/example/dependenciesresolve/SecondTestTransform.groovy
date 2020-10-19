package com.example.dependenciesresolve

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import org.apache.commons.io.FileUtils


class SecondTestTransform extends Transform {

    private DependenceTransformExtension mExtension

    SecondTestTransform(DependenceTransformExtension extension) {
        mExtension = extension
    }

    //用于指明本Transform的名字，也是代表该Transform的task的名字
    @Override
    String getName() {
        return "SecondTestTransform"
    }

    //用于指明Transform的输入类型，可以作为输入过滤的手段。
    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    //用于指明Transform的作用域
    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    //是否增量编译
    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation invocation) {
        println("SecondTestTransform transform begin...")
        def inputs = invocation.inputs
        def outputProvider = invocation.getOutputProvider()

        //删除之前的输出
        if (outputProvider != null) {
            outputProvider.deleteAll()
        }

        inputs.forEach { TransformInput input->
            input.directoryInputs.forEach { DirectoryInput directoryInput->
                def dest = outputProvider.getContentLocation(directoryInput.name,
                        directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)

                //将 input 的目录复制到 output 指定目录
                try {
                    FileUtils.copyDirectory(directoryInput.file, dest)
                } catch(Exception ex) {
                    ex.printStackTrace()
                    println(ex.getMessage())
                }
            }

            input.jarInputs.forEach { JarInput jarInput ->
                def dest = outputProvider.getContentLocation(jarName + md5Name,
                        jarInput.contentTypes, jarInput.scopes, Format.JAR)

                try {
                    FileUtils.copyFile(jarInput.file, dest)
                } catch (Exception ex) {
                    ex.printStackTrace()
                    println(ex.getMessage())
                }
            }
        }
        println("SecondTestTransform transform end...")

    }

}