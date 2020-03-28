package com.lms.Utils;

import pers.missionlee.webmagic.spider.sankaku.manager.SourceManager;

/**
 * @description:
 * @author: Mission Lee
 * @create: 2019-07-24 23:23
 */
public class TestSelfJar {
    public static void main(String[] args) {
        SourceManager sourceManager = new SourceManager("E:\\ROOT");
        sourceManager.findDuplicationDir();
        System.out.println(sourceManager.getArtistListByJson(SourceManager.SourceType.SANKAKU));
    }
}
