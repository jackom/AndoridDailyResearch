package com.example.second

import org.gradle.api.Plugin
import org.gradle.api.Project

public class SecondPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        def myExtension = project.extensions.create('pluginsrc', MyExtension)

        println("========================")
        project.task('testPlugin123') {
            println("message is: " + myExtension.message)
            doLast {
                println("doLast message is: " + myExtension.message)
            }
        }
        println("========================")
    }
}