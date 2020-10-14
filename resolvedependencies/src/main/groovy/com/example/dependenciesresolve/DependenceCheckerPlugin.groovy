package com.example.dependenciesresolve

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.result.DependencyResult
import org.gradle.api.artifacts.result.ResolvedDependencyResult

/**
 * Created by yangtianrui on 2018/7/8.
 * link: https://github.com/yangtianrui95/RuntimeDependenceChecker
 * 运行时依赖检测插件
 */

class DependenceCheckerPlugin implements Plugin<Project> {


    final static def EXT_NAME = "DependenceChecker"
    final static def PLUGIN_NAME = "RDCTask"

    @Override
    void apply(Project target) {

        // 创建一个Ext数据
        target.extensions.create(EXT_NAME, DependenceChecker)
        println("=============apply===============")
        DependenceChecker ext = target.extensions.DependenceChecker

        // 向Project中追加一个Task
        target.task(PLUGIN_NAME) {

            Map depMap = new HashMap()
            String checkMode = "${ext.variant}runtimeclasspath"
            println("checkMode is: $checkMode")
            println target.configurations

//            println target.tasks


            target.tasks.each { Task task ->
                if (task.name.equalsIgnoreCase("dependencies")) {
                    println("task.name is: ${task.name}")
                    println("task.group is: ${task.group}")
                    println("task.extensions is: ${task.extensions}")
                    println("task.taskDependencies is: ${task.taskDependencies}")
                    println("task.convention is: ${task.convention}")


                }
            }

//            target.dependencies.each { Dependency dependency ->
//                println("dependency.group is: ${dependency.group}")
//                println("dependency.name is: ${dependency.name}")
//                println("dependency.version is: ${dependency.version}")
//                println("dependency.metaClass is: ${dependency.metaClass}")
//            }

//            target.dependencies.findAll {
//                println("target.dependencies is: ${it}")
//            }

//            target.android.applicationVariants.all { variant ->
//                List<File> deps = new ArrayList<>()
//                FileCollection fileCollection = variant.variantData.scope
//                        .getArtifactFileCollection(
//                                AndroidArtifacts.ConsumedConfigType.RUNTIME_CLASSPATH,
//                                AndroidArtifacts.ArtifactScope.PROJECT,
//                                AndroidArtifacts.ArtifactType.JAVA_RES
//                        )
//                fileCollection.each { file ->
//                    println("file.name is: ${file.name}")
//                    println("file.absolutePath is: ${file.absolutePath}")
//                    deps.add(file)
//                }
//            }


//            Configuration tmpConfiguration
//            try {
//                // 3.x
//                tmpConfiguration = project.configurations."${ext.variant}CompileClasspath"
//            } catch (Exception e) {
//                // 2.x
//                tmpConfiguration = project.configurations."_${ext.variant}Compile"
//            }
//
//            tmpConfiguration.resolvedConfiguration.lenientConfiguration.allModuleDependencies.each {
//                def identifier = it.module.id
//                println("${identifier.group}:${identifier.name}:${identifier.version}")
//            }


            target.configurations.each { Configuration conf ->

                //  考虑多个Flavor的情况
                println("conf.name is: ${conf.name}")
                if (conf.name.equalsIgnoreCase("coreLibraryDesugaring")) {
                    // 获取所有依赖信息
                    conf.incoming.resolutionResult.root.dependencies.each { dr ->
                        resolveDependencies(depMap, dr)
                    }
                    printDepInfo(depMap)
                    reportDepInfo(depMap, ext.abortBuild)
                }
            }

//            DefaultDomainObjectCollection<BaseVariant> variants
//            if (target.plugins.hasPlugin(AppPlugin.class)) {
//                variants = target.android.applicationVariants
//            } else if (target.plugins.hasPlugin(LibraryPlugin.class)) {
//                variants = target.android.libraryVariants
//            } else {
//                return
//            }
//            variants.all { variant ->
//                List<File> deps = new ArrayList<>()
//                FileCollection fileCollection =  variant.variantData.scope
//                        .getArtifactFileCollection(
//                                AndroidArtifacts.ConsumedConfigType.RUNTIME_CLASSPATH,
//                                AndroidArtifacts.ArtifactScope.PROJECT,
//                                AndroidArtifacts.ArtifactType.JAVA_RES
//                        )
//                fileCollection.each {file ->
//                    deps.add(file)
//                }
//            }


        }
        target.tasks.findByName('preBuild').dependsOn(target.tasks.findByName(PLUGIN_NAME))
    }

    static def printDepInfo(Map depMap) {
        depMap.each { k, v ->
            println(k + " => " + v.size())
        }
    }

    static def reportDepInfo(Map depMap, boolean abortBuild) {

        depMap.each { k, v ->
            if (v.size() > 1) {
                if (abortBuild) {
                    println('duplicate files: ${k}')
                    throw new RuntimeException("${k} has duplicate dependences, please resolve it...\n\n${v}")
                }
            }
        }
    }


    static def resolveDependencies(Map<String, Set<String>> map, DependencyResult dr) {
        println("=============resolveDependencies===============")
        def depName = dr.requested.displayName
        if (dr != null && !depName.contains("project")) {
            String[] depSplit = depName.split(":")
            if (depSplit.length > 2) {
                def packageName = depSplit[0] + depSplit[1]
                def list = map.get(packageName)
                if (list == null) {
                    list = new HashSet<String>()
                    map.put(packageName, list)
                }
                list.add(depName)
            }
        }
        if (dr instanceof ResolvedDependencyResult) {
            dr.selected.dependencies.each { subDr ->
                resolveDependencies(map, subDr)
            }
        }
    }


}
