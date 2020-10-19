package com.example.dependenciesresolve

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils

import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry


class TestTransform extends Transform {

    private DependenceTransformExtension mExtension

    TestTransform(DependenceTransformExtension extension) {
        mExtension = extension
    }

    //用于指明本Transform的名字，也是代表该Transform的task的名字
    @Override
    String getName() {
        return "TestTransform"
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
        println("TestTransform transform begin...")
        def inputs = invocation.inputs
        def outputProvider = invocation.getOutputProvider()

        //删除之前的输出
        if (outputProvider != null) {
            outputProvider.deleteAll()
        }

        inputs.forEach { TransformInput input->
            input.directoryInputs.forEach { DirectoryInput directoryInput->
                //往类中注入代码
//                injectClick(directoryInput.file.getAbsolutePath(), "com", mProject)

                println "directoryInput.name is: ${directoryInput.name}"
                println "directoryInput.file.name is: ${directoryInput.file.name}"
                println "directoryInput.file.absolutePath is: ${directoryInput.file.absolutePath}"
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

            List<String> channels = new ArrayList<>()
            input.jarInputs.forEach { JarInput jarInput ->
                //往类中注入代码
//                injectClick(jarInput.file.getAbsolutePath(), "com.netease", mProject)

                //重命名输出文件（同目录 copyFile 会冲突）
                def jarName = jarInput.name
                def md5Name = jarInput.file.hashCode()
                if(jarName.endsWith(".jar")){
                    jarName = jarName.substring(0, jarName.length() - 4)
                }
//                println "jarName is: ${jarName}"
//                println "jarInput.name is: ${jarInput.name}"
//                println "jarInput.file.name is: ${jarInput.file.name}"
//                println "jarInput.file.absolutePath is: ${jarInput.file.absolutePath}"

                if (jarInput.file.name.startsWith("jetified-")
                        && jarInput.file.name.contains("qys_sdk")) {
                    //待处理 遍历所有jar包，判断是否存在相同的依赖
                    println "jarName is: ${jarName}"
                    println "jarInput.name is: ${jarInput.name}"
                    println "jarInput.file.name is: ${jarInput.file.name}"
                    println "jarInput.file.absolutePath is: ${jarInput.file.absolutePath}"

                    //逐个解压，并拿到压缩包
                    println "jarInput.file.name contains(\"qys_sdk\")"

//                        def dest = outputProvider.getContentLocation(jarName + md5Name,
//                                jarInput.contentTypes, jarInput.scopes, Format.JAR)

                    try {
                        //jar包解压的临时位置
//                            String absolutePath = jarInput.file.absolutePath
//                            String realPath = absolutePath.substring(0, absolutePath.lastIndexOf("\\"))
//                            def tmpDir = absolutePath.replace(".jar", File.separator)
//                            println "absolutePath is: $absolutePath"
//                            println "tmpDir is: $tmpDir"
//                            println "realPath is: $realPath"
//                            String realPath2222 = realPath.substring(0, realPath.lastIndexOf("\\"))
//                            println "realPath2222 is: $realPath2222"
//                            File newFile = new File(realPath2222, "jetified-qys_sdk_v3.1.8-runtime.jar")


                        //读取jar包
                        File tmpFile = new File(jarInput.file.parent + File.separator + "classes_tmp.jar")
                        if (tmpFile.exists()) {
                            tmpFile.delete()
                        }

                        JarOutputStream tmpJarOutputStream = new JarOutputStream((new FileOutputStream((tmpFile))))

                        //jar文件
                        JarFile jarFile = new JarFile(jarInput.file)
                        //拿到所有的jar中的文件
                        Enumeration<JarEntry> enumeration = jarFile.entries()

                        //用于保存JAR文件，修改JAR中的class
                        while (enumeration.hasMoreElements()) {
                            JarEntry jarEntry = enumeration.nextElement()
                            String entryName = jarEntry.name

//                                println "entryName is: $entryName"

                            ZipEntry zipEntry = new ZipEntry(entryName)
                            if (zipEntry.directory) {
                                continue
                            }

                            if (!entryName.startsWith("com/qq/e")) {

                                //读取jar中的文件输入流
                                InputStream inputStream = jarFile.getInputStream(jarEntry)
                                tmpJarOutputStream.putNextEntry(zipEntry)
                                tmpJarOutputStream.write(IOUtils.toByteArray(inputStream))

                            } else {
//                                println "com/qq/e... ${entryName}"
//                                String needPkgName = entryName.replace("/", ".")
                                if (!channels.contains("com.qq.e")) {
                                    channels.add("com.qq.e")
                                }

//                                input.jarInputs.remove(jarInput)
//                                InputStream inputStream = jarFile.getInputStream(jarEntry)
//                                tmpJarOutputStream.putNextEntry(zipEntry)
//                                tmpJarOutputStream.write(IOUtils.toByteArray(inputStream))
                            }


                            //删除jar包中的指定包名以及相应的文件



                            tmpJarOutputStream.closeEntry()
                        }

                        //结束
                        tmpJarOutputStream.close()
                        jarFile.close()




//                            directoryInput.file.eachFileRecurse { File file ->
//                                println "directoryInput.file : ${file.name}"
//                                println "directoryInput.file absolutePath: ${file.absolutePath}"
//                            }


                        //重新打包成jar包









                        jarInput.file.delete()
//
//                            File mSaveFile = new File(realPath, "jetified-qys_sdk_v3.1.8-runtime.jar");
//
//                            if (!mSaveFile.exists()) {
//                                mSaveFile.createNewFile()
//                            }
//                            println "mSaveFile is: ${mSaveFile.absolutePath}"
//
//
//                            //获得原文件流
//                            FileInputStream inputStream = new FileInputStream(newFile);
//                            byte[] data = new byte[1024];
//                            //输出流
//                            FileOutputStream outputStream =new FileOutputStream(mSaveFile);
//                            //开始处理流
//                            while (inputStream.read(data) != -1) {
//                                outputStream.write(data);
//                            }
//                            inputStream.close();
//                            outputStream.close();

                        def newDest = outputProvider.getContentLocation(jarName + md5Name,
                                jarInput.contentTypes, jarInput.scopes, Format.JAR)

                        File renameFile = new File(jarInput.file.parent + File.separator + jarInput.file.name)
                        tmpFile.renameTo(renameFile)

                        println "renameFile.absolutePath is : ${renameFile.absolutePath}"

                        FileUtils.copyFile(renameFile, newDest)
                        tmpFile.delete()

                    } catch(Exception ex) {
                        ex.printStackTrace()
                        println(ex.getMessage())
                    }
                } else {
                    def dest = outputProvider.getContentLocation(jarName + md5Name,
                            jarInput.contentTypes, jarInput.scopes, Format.JAR)

                    println "dest.file.name is: ${dest.name}"
                    println "dest.file.absolutePath is: ${dest.absolutePath}"

                    try {
//                    scanFilesAndInsertCode(jarInput.file.getAbsolutePath());
                        FileUtils.copyFile(jarInput.file, dest)
                    } catch(Exception ex) {
                        ex.printStackTrace()
                        println(ex.getMessage())
                    }
                }
            }
            mExtension.setChannels(channels)
        }
        println("TestTransform transform end...")
    }

}