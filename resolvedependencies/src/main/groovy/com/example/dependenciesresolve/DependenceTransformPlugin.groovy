package com.example.dependenciesresolve

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class DependenceTransformPlugin implements Plugin<Project> {

    @Override
    void apply(Project target) {

        println "DependenceTransformPlugin begin execute..."
        target.getExtensions().findByType(BaseExtension.class)
                .registerTransform(new TestTransform());
    }

}