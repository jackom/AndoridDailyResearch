package com.example.dependenciesresolve

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.api.ApplicationVariant
import org.gradle.api.Plugin
import org.gradle.api.Project

class DependenceTransformPlugin implements Plugin<Project> {

    @Override
    void apply(Project target) {

        println "DependenceTransformPlugin begin execute..."
        target.getExtensions().findByType(BaseExtension.class)
                .registerTransform(new TestTransform());

        def android = target.extensions.getByType(AppExtension)
        android.applicationVariants.all { ApplicationVariant variant ->
            variant.getCompileClasspath().each { File file ->
                println "file.name is: ${file.name}"
                println "file.absolutePath is: ${file.absolutePath}"
            }
        }
    }

}