package com.example.dependenciesresolve

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.api.ApplicationVariant
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileCollection

class DependenceTransformPlugin implements Plugin<Project> {

    @Override
    void apply(Project target) {

        println "DependenceTransformPlugin begin execute..."

        DependenceTransformExtension dependenceTransformExtension = target.extensions.create(
                "DependenceTransform", DependenceTransformExtension)

        BaseExtension baseExtension = target.getExtensions().findByType(BaseExtension.class)

        baseExtension.registerTransform(new TestTransform(dependenceTransformExtension))
        baseExtension.registerTransform(new SecondTestTransform(dependenceTransformExtension))

        println "DependenceTransformPlugin execute finish..."

//        def android = target.extensions.getByType(AppExtension)
//        android.applicationVariants.all { ApplicationVariant variant ->
//            variant.getCompileClasspath().each { File file ->
//                println "file.absolutePath is: ${file.absolutePath}"
//                println "file.name is\n: ${file.name}"
//            }
//        }

//        ConfigurableFileCollection configurableFileCollection = target.files("../app/build.gradle")
//        //遍历每一个文件
//        configurableFileCollection.files.each {
//            File file ->
//                println "file.text is: ${file.text}"
//        }



    }

}