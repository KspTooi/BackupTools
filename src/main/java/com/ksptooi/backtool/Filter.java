package com.ksptooi.backtool;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter implements FilenameFilter {

    private final List<String> excludes;

    public Filter(List<String> list){
        this.excludes = list;
    }

    /**
     * Tests if a specified file should be included in a file list.
     *
     * @param dir  the directory in which the file was found.
     * @param name the name of the file.
     * @return <code>true</code> if and only if the name should be
     * included in the file list; <code>false</code> otherwise.
     */
    @Override
    public boolean accept(File dir, String name) {

        boolean isExclude = false;

        for(String item : excludes){

            Pattern pattern = Pattern.compile(item);
            Matcher matcher = pattern.matcher(name);

            if(matcher.matches()){
                isExclude = true;
                break;
            }

        }

        return !isExclude;
    }


}
