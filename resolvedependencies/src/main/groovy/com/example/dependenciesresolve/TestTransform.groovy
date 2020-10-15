package com.example.dependenciesresolve

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import org.apache.commons.io.FileUtils
import javassist.ClassPool
import javassist.CtClass
import javassist.CtMethod


public class TestTransform extends Transform {

    //用于指明本Transform的名字，也是代表该Transform的task的名字
    @Override public String getName() {
        return "TestTransform";
    }

    //用于指明Transform的输入类型，可以作为输入过滤的手段。
    @Override public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    //用于指明Transform的作用域
    @Override public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    //是否增量编译
    @Override public boolean isIncremental() {
        return false;
    }

    @Override public void transform(TransformInvocation invocation) {
        println("TestTransform transform begin...");
        def inputs = invocation.inputs
        def outputProvider = invocation.getOutputProvider()
        inputs.each { TransformInput input->
            input.directoryInputs.each { DirectoryInput directoryInput->
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
                    println(e.getMessage());
                }
            }

            input.jarInputs.each { JarInput jarInput ->
                //往类中注入代码
//                injectClick(jarInput.file.getAbsolutePath(), "com.netease", mProject)

                //重命名输出文件（同目录 copyFile 会冲突）
                def jarName = jarInput.name
                def md5Name = jarInput.file.hashCode()
                if(jarName.endsWith(".jar")){
                    jarName = jarName.substring(0, jarName.length() - 4)
                }
                println "jarName is: ${jarName}"
                println "jarInput.name is: ${jarInput.name}"
                println "jarInput.file.name is: ${jarInput.file.name}"
                println "jarInput.file.absolutePath is: ${jarInput.file.absolutePath}"

                if (jarInput.file.name.startsWith("jetified-")) {

                    if (jarInput.file.name.contains("qys_sdk")) {
                        //逐个解压，并拿到压缩包
                        println "jarInput.file.name contains(\"qys_sdk\")"

                        def dest = outputProvider.getContentLocation(jarName + md5Name,
                                jarInput.contentTypes, jarInput.scopes, Format.JAR)

                        try {
                            //jar包解压的临时位置
                            String absolutePath = jarInput.file.absolutePath
                            String realPath = absolutePath.substring(0, absolutePath.lastIndexOf("\\"))
                            def tmpDir = absolutePath.replace(".jar", File.separator)
                            println "absolutePath is: $absolutePath"
                            println "tmpDir is: $tmpDir"
                            println "realPath is: $realPath"
                            String realPath2222 = realPath.substring(0, realPath.lastIndexOf("\\"))
                            println "realPath2222 is: $realPath2222"
                            File newFile = new File(realPath2222, "jetified-qys_sdk_v3.1.8-runtime.jar")
//                            def tmpFileDirectory = new File(tmpDir)
//                            tmpFileDirectory.mkdirs()
//                            println "tmpFileDirectory is: ${tmpFileDirectory.absolutePath}"
//                            File tmpFile = new File(tmpFileDirectory, "tmp.txt")
//                            if (null != tmpFile && !tmpFile.exists()) {
//                                tmpFile.createNewFile()
//                            }
//                            try {
//                                FileWriter fw = new FileWriter(tmpFile);
//                                fw.write("formatDate..." + " " + "messageTitle" + "\n");
//                                fw.write("message" + "\n");
//                                fw.write("\n");
//                                fw.close();
//
//                                println "tmpFile.absolutePath is: ${tmpFile.absolutePath}"
//                                println "realPath is: ${realPath}"
//                                MyZipUtils.zip(tmpFileDirectory.absolutePath, dest.absolutePath)
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                    scanFilesAndInsertCode(jarInput.file.getAbsolutePath());


//                            File mSaveFile = new File(realPath);

//                            String local_file = mSaveFile.getAbsolutePath() + File.separator + "jetified-qys_sdk_v3.1.8-runtime.jar";

                            File mSaveFile = new File(realPath, "jetified-qys_sdk-runtime.jar");

                            if (!mSaveFile.exists()) {
                                mSaveFile.createNewFile()
                            }
                            println "mSaveFile is: ${mSaveFile.absolutePath}"


                            //获得原文件流
                            FileInputStream inputStream = new FileInputStream(newFile);
                            byte[] data = new byte[1024];
                            //输出流
                            FileOutputStream outputStream =new FileOutputStream(mSaveFile);
                            //开始处理流
                            while (inputStream.read(data) != -1) {
                                outputStream.write(data);
                            }
                            inputStream.close();
                            outputStream.close();


                            jarInput.file.delete()

                            FileUtils.copyFile(mSaveFile, dest)
//                            tmpFile.delete()
                        } catch(Exception ex) {
                            ex.printStackTrace()
                            println(ex.getMessage());
                        }

                    } else {
                        println "jarInput.file.name jetified is: ${jarInput.file.name}"
                        def dest = outputProvider.getContentLocation(jarName + md5Name,
                                jarInput.contentTypes, jarInput.scopes, Format.JAR)

                        println "dest.file.name is: ${dest.name}"
                        println "dest.file.absolutePath is: ${dest.absolutePath}"

                        try {
    //                    scanFilesAndInsertCode(jarInput.file.getAbsolutePath());
                            FileUtils.copyFile(jarInput.file, dest)
                        } catch (Exception ex) {
                            ex.printStackTrace()
                            println(ex.getMessage());
                        }
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
                        println(ex.getMessage());
                    }
                }
            }
        }
    }

    private void scanFilesAndInsertCode(String path) throws Exception {
        ClassPool classPool = ClassPool.getDefault();
        classPool.appendClassPath(path);//将当前路径加入类池,不然找不到这个类
        CtClass ctClass = classPool.getCtClass("com.example.testdemos.PluginTest");
        if (ctClass == null) {
            return;
        }
        if (ctClass.isFrozen()) {
            ctClass.defrost();
        }
        CtMethod ctMethod = ctClass.getDeclaredMethod("init");

        String insetStr = "System.out.println(\"the codes to be insert!\");";
        ctMethod.insertAfter(insetStr);//在方法末尾插入代码
        ctClass.writeFile(path);
        ctClass.detach();//释放
    }
}