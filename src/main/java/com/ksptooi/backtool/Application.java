package com.ksptooi.backtool;

import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application {



    public static void main(String[] args) throws FileNotFoundException {

        if(args.length < 1){
            System.out.println("not found parameter at 1");
            return;
        }

        File config = new File(args[0]);

        if(!config.exists()){
            System.out.println("not found config at "+args[0]);
            return;
        }

        InputStream is = new FileInputStream(config);

        Yaml yaml = new Yaml();


        Config cfg = yaml.loadAs(is, Config.class);

        File origin = new File(cfg.getOrigin());
        File dest = new File(cfg.getDestination());

        if(!origin.exists() || !origin.isDirectory()){
            System.out.println("origin not found or not a directory");
            return;
        }
        if(!dest.exists() || !dest.isDirectory()){
            System.out.println("destination not found or not a directory");
            return;
        }

        Filter filter = new Filter(cfg.getIgnore());



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss SSS");

        System.out.println("create backup director at:" + dest.getPath());

        String backupDirName = "/CACHE "+sdf.format(new Date());

        File destDirectory = new File(dest,backupDirName);

        if(!destDirectory.mkdir()){
            System.out.println("create backup directory failed!");
            return;
        }

        System.out.println("success "+ backupDirName);

        File[] files = origin.listFiles(filter);

        if(files == null){
            System.out.println("scan failed ");
            return;
        }


        long size = 0;

        for (File f : files){

            try {
                System.out.println("copy:"+f.getName());

                if(f.isDirectory()){
                    FileUtils.copyDirectory(f,new File(destDirectory,f.getName()));
                    FileUtils.deleteDirectory(f);
                }

                if(!f.isDirectory()){
                    Files.copy(f.toPath(),new File(destDirectory,f.getName()).toPath());
                    if(!f.delete()){
                        System.out.println("cannot delete file:"+f.getName());
                    }
                }

                size = size + f.getTotalSpace();

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("cannot copy file:"+f.getName());
            }

        }

        System.out.println("success totalSize:"+size);


    }


}
