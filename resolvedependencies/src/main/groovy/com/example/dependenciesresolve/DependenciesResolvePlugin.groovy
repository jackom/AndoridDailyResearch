package com.example.dependenciesresolve

import org.gradle.api.Plugin
import org.gradle.api.Project

class DependenciesResolvePlugin implements Plugin<Project> {

    @Override
    void apply(Project target) {

        println "begin excecute..."
    }

}