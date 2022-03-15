package com.example.asupporter.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainCrawler {

    @Autowired(required = false)
    private NckCrawler nck;

    @Autowired(required = false)
    private BusanCrawler busan;

    @Autowired(required = false)
    private UlsanCrawler ulsan;

    @Autowired(required = false)
    private KBSCrawler kbs;

    @Autowired(required = false)
    private SuwonCityChorusCrawler suwon;

    @Autowired(required = false)
    private JejuArtGroupCrawler jeju;

    @Autowired(required = false)
    private SeongnamCityArtGroupCrawler seongnam;
    
    @Autowired(required = false)
    private DaeguCityArtGroupCrawler daegu;

    @Autowired(required = false)
    private BucheonCityArtGroupCrawler bucheon;

    @Autowired(required = false)
    private ChuncheonCityArtGroupCrawler chuncheon;

    @Autowired(required = false)
    private GangneungCityArtGroupCrawler gangneung;

    @Autowired(required = false)
    private JeonjuCityArtGroupCrawler jeonju;

    @Autowired(required = false)
    private GwangjuCitySymphonyOrchestraCrawler gwangju;

    @Autowired(required = false)
    private SuncheonCityArtGroupCrawler suncheon;

    @Autowired(required = false)
    private ChangwonCityArtGroupCrawler changwon;

    @Autowired(required = false)
    private AnsanCityArtGroupCrawler ansan;

    @Autowired(required = false)
    private AnyangCityChorusCrawler anyang;

    @Autowired(required = false)
    private IncheonCityArtGroupCrawler incheon;

    @Autowired(required = false)
    private GangnamArtGroupCrawler gangnam;

    @Autowired(required = false)
    private SeoulCityArtGroupCrawler seoulCity;

    @Autowired(required = false)
    private SymphonysongCrawler symphomy;

    @Autowired(required = false)
    private DaejeonCitySOCrawler daejeonSO;

    @Autowired(required = false)
    private DaejeonCityChorusCrawler daejeonC;

    @Autowired(required = false)
    private WonjuCityArtGroupCrawler wonju;

    @Autowired(required = false)
    private CheongjuCityArtGroupCrawler cheongju;

    public void mainCrawler() {
        busan.busanCrawler();
        nck.nckCrawler();
        kbs.kbsCrawler();
        ulsan.ulsanCrawler();
        suwon.suwonCityChorusCrawler();
        jeju.jejuArtGroupCrawler();
        seongnam.seongnamCityArtGroupCrawler();
        daegu.daeguCityArtGroupCrawler();
    	bucheon.bucheonCityArtGroupCrawler();
    	chuncheon.chuncheonCityArtGroupCrawler();
    	gangneung.gangneungCityArtGroupCrawler();
    	jeonju.jeonjuCityArtGroupCrawler();
    	gwangju.gwangjuCitySymphonyOrchestraCrawler();
    	suncheon.suncheonCityArtGroupCrawler();
    	changwon.changwonCityArtGroupCrawler();
    	ansan.ansanCityArtGroupCrawler();
    	anyang.anyangCityChorusCrawler();
    	incheon.incheonCityArtGroupCrawler();
    	gangnam.gangnamArtGroupCrawler();
    	seoulCity.seoulCityArtGroupCrawler();
    	symphomy.symphonysongCrawler();
    	daejeonSO.daejeonCitySOCrawler();
    	daejeonC.daejeonCityChorusCrawler();
    	wonju.wonjuCityArtGroupCrawler();
    	cheongju.cheongjuCityArtGroupCrawler();  // 상위 고정된 공지사항과 하단 내용에 중복이 있을 수도 있음
    }
}
