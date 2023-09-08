package csharp.moira;
//set main class to 「org.athomeprojects.Moira.Moira」 to run original Moira

import java.util.*;

import org.athomeprojects.base.*;

public class Launch {

	public Launch() {
		int[] birth = { 1984, 2, 4, 1, 30 };
		int[] now = { 2022, 10, 28, 0, 0 };
		Moira.mri.setBirthTime(birth);
		Moira.mri.setNowTime(now);
		Moira.init();
		String[] country_list = Moira.mri.getCountryList();
		Log.c(Arrays.toString(country_list));
		String[] city_list = Moira.mri.getCityList(country_list[33]);
		Log.c(Arrays.toString(city_list));
		String[] zone_list = Moira.mri.getZoneList();
		Log.c(Arrays.toString(zone_list));		
		Moira.mri.setLocation(country_list[33], city_list[6], zone_list[312]); // 台灣，台南，Asia/Taipei
		Moira.run();
		ShowData();
		Moira.disposeMoira();
		switch (1) {
		case 1:
			int[] start = { 2022, 8, 31, 0, 0 };
			Log.c("==getCrossOver==");
			int[] crov_time = new int[5];
			String[] pos = new String[2];
			//planet_no 0~6:日月金木水火土 10~13:計羅炁孛
			crov_time = MRI.getCrossOver(4, start, pos, country_list[33], city_list[6], zone_list[312]);			
			Log.c("起　　: " + pos[0]);
			Log.c(pos[1]);
			Log.c(Arrays.toString(crov_time));
			break;
		case 2:
			break;
		case 3:
			break;
		}
	}

	public static void main(String[] args) {
		new Launch();
	}

	public void ShowData() {

		String selector = "1";
		int planet = 10; // 0~6,10~13
		int[] birth_date = new int[5];
		int[] now_date = new int[5];
		int[] tmp_date = new int[5];

		Moira.mri.getBirthDate(birth_date);
		Moira.mri.getNowDate(now_date);

		Log.OpenLogFile();
		if (selector.contains("1")) {
			Log.cf("Country/City/Zone : " + Moira.mri.getCountry() + " , " + Moira.mri.getCity() + " , "
					+ Moira.mri.getZone());
			Log.cf("Birth      : " + Moira.mri.getBirthString());
			Log.cf("Birth(陰曆) : " + Moira.mri.getBirthString(true));
			Log.cf("Now        : " + Moira.mri.getNowString());
			Log.cf("Now(陰曆)   : " + Moira.mri.getNowString(true));
			Log.cf("Birth 未: " + Moira.mri.getKiShinNames("未"));
			Log.cf("Now   未: " + Moira.mri.getKiShinNames("未", true));
			Log.cf("Birth喜神: " + Moira.mri.getYearShinStar("喜神"));
			Log.cf("Now  喜神: " + Moira.mri.getYearShinStar("喜神", true));
			Log.cf("Birth計主: " + Moira.mri.getStarShin("計", "all"));
			Log.cf("Birth計主: " + Moira.mri.getStarShin("計", "year"));
			Log.cf("Birth天耗: " + Moira.mri.getKiShinStar("天耗", false, true));
			Log.cf("getHalfStellarSigns: " + Arrays.toString(Moira.mri.getHalfStellarSigns()));
			Log.cf("getStellarSigns  : " + Arrays.toString(Moira.mri.getStellarSigns()));
			
			String tmp = "";
			for (int i = 0; i < 22; i += 1) {
				String name = MRI.getPlanetName(i);
				int state = Moira.mri.getPlanetState(i);
				String status = Moira.mri.getPlanetStatus(i);
				if (status!=null) status = status.replace("@", "");
				String state_s = MRI.getStateString(state);
				tmp = tmp + name + "(" + state_s + ")" + "(" + status + ")" + "  ";								
			}
			Log.cf(tmp);
			
			Log.cf("MRI.getPlanetPos  : " + Moira.mri.getPlanetPos(planet));
			Log.cf("MRI.getPlanetPosString  : " + Moira.mri.getPlanetPosString(planet));			
			Log.cf(Moira.mri.getSolarTermsPage(true));
			Log.cf(Arrays.toString(Moira.mri.getSolarTermsInt(true)[0]));			
			Log.cf("getFortuneScopes: " + Arrays.toString(Moira.mri.getFortuneScopes()));
			Log.cf("getNowYearPos: " + Arrays.toString(Moira.mri.getNowYearPos()));
			Log.cf("getNowYearPosString: " + Arrays.toString(Moira.mri.getNowYearPosString()));
			Log.cf("getEightWords: " + Arrays.toString(Moira.mri.getEightWords()));			
			Log.cf("getWeakHouse: " + Arrays.toString(Moira.mri.getWeakHouse()));
			Log.cf("getSolidHouse: " + Arrays.toString(Moira.mri.getSolidHouse()));
			Log.cf("getChildLimit: " + Moira.mri.getChildLimit(false));
			Log.cf("getChildLimit: " + Moira.mri.getChildLimit(Moira.mri.getCurrentAge(),","));		
			Log.cf("getSmallLimit: " + Moira.mri.getSmallLimit(Moira.mri.getCurrentAge(),","));			
			Log.cf("getMonthLimit: " + Moira.mri.getMonthLimit(Moira.mri.getCurrentAge(),","));
			Log.cf("getFlyLimit: " + Moira.mri.getFlyLimit(Moira.mri.getCurrentAge(),",",(int) Moira.mri.getFortuneScopes()[0]+1));
			Log.cf("getHouse: " + Moira.mri.getHouse());
			Log.cf("getDayOrNight: " + Moira.mri.getDayOrNight());
			Log.cf("good_styles: " + Moira.mri.getGoodPattern());
			Log.cf("bad_styles: " + Moira.mri.getBadPattern());
			Log.cf("getBirthRiseTime: " + Arrays.toString(Moira.mri.getBirthRiseTime()));
			Log.cf("getBirthSetTime: " + Arrays.toString(Moira.mri.getBirthSetTime()));
			
		}
		if (selector.contains("2")) {
			Log.cf("AngleMarkerArray : " + Arrays.toString(Moira.mri.data.getAngleMarkerArray(true, false, 1)));
			Log.cf(("AngleMarkerEnable : " + Moira.mri.data.getAngleMarkerEnable()));
			Log.cf("AspectColorArray(相位顯示顏色) : " + Arrays.toString(Moira.mri.data.getAspectColorArray(true)));
			Log.cf("AspectDegreeArray(相位角度) : " + Arrays.toString(Moira.mri.data.getAspectDegreeArray(true)));
			Log.cf("AspectDisplayArray(相位顯示選擇) : " + Arrays.toString(Moira.mri.data.getAspectDisplayArray(true)));
			Log.cf("AspectOrbArray : " + Arrays.toString(Moira.mri.data.getAspectOrbArray()));
			Log.cf("AspectSignArray(相位中文說法) : " + Arrays.toString(Moira.mri.data.getAspectSignArray(true)));
			Log.cf("AstroData(西洋星盤資訊) : " + Arrays.toString(Moira.mri.data.getAstroData()));
			Log.cf("ChildLimit(童限) : " + Moira.mri.data.getChildLimit(false));
			Log.cf("ChildLimit(童限@18) : " + Moira.mri.data.getChildLimit(18, ","));
			Log.cf("Cal : " + Moira.mri.data.getCal());
			Log.cf("getCuspArray : " + Arrays.toString(Moira.mri.data.getCuspArray()));
			Log.cf("DayOrNight : " + Moira.mri.data.getDayOrNight());
			int[] start = new int[5];
			// Log.cf("DateAtPlanetPos : " +
			// Arrays.toString(Moira.mri.data.getDateAtPlanetPos(0, true, 0, "台灣",
			// "台北",
			// "Asia/Taipei", start, false))); //call failed.
			Log.cf("DateAtPlanetPos : call failed"); // not needed so far.
														// bypass it
			// Log.cf("DateAtSunPos : " +
			// Arrays.toString(Moira.mri.data.getDateAtSunPos(0, "台灣", "台北",
			// "Asia/Taipei", start, false, false))); call failed
			Log.cf("DateAtSunPos : call failed"); // not needed so far. bypass
													// it
			Hashtable[] displayTable = new Hashtable[2];
			displayTable = Moira.mri.data.getDisplayTable();
			Log.cf("DisplayTable is an array of Hashtable");
			Log.cf("DrawSize : " + Arrays.toString(Moira.mri.data.getDrawSize()));
			Log.cf("EightCharOverride : " + Moira.mri.data.getEightCharOverride());
			SearchRecord[] s_record = Moira.mri.data.getEclipseData();
			Log.cf("EclipseData is an array of SearchRecord");
			Log.cf("FlyLimit(飛限) : " + Moira.mri.data.getFlyLimit(18, ",", 12));
			Log.cf("House(命宮) : " + Moira.mri.data.getHouse());
			Log.cf("HorizData : " + Arrays.toString(Moira.mri.data.getHorizData()));
			double[] loc_db = new double[5];
			Moira.mri.data.getLocation(loc_db);
			Log.cf("Location(經緯度?) : " + Arrays.toString(loc_db));
			Log.cf("MergeName : " + Moira.mri.data.getMergeName("name1", "name2"));
			Log.cf("MonthLimit(月限@1歲) : " + Moira.mri.data.getMonthLimit(1, ","));
			Log.cf("MonthLimit(月限@40歲) : " + Moira.mri.data.getMonthLimit(40, ","));
			Log.cf("MountainPos(true) : " + Moira.mri.data.getMountainPos(true));
			Log.cf("MountainPos(false) : " + Moira.mri.data.getMountainPos(false));
			Log.cf("NoColor : " + Moira.mri.data.getNoColor());
			Log.cf("OverridenStatus : " + Moira.mri.data.getOverridenStatus());
			Log.cf("OverrideString : " + Moira.mri.data.getOverrideString());
			Log.cf("OrderArray : " + Arrays.toString(Moira.mri.data.getOrderArray()));
			Log.cf("PlanetOffset : " + Moira.mri.data.getPlanetOffset(0, 0));
		}
		if (selector.contains("3")) {
			Log.cf("PlanetPos of birth");
			Log.cf("PlanetPos(0) : " + Moira.mri.data.getPlanetPos(0, Moira.mri.result.birth_sign_pos[0])); // 第二個傳入參數degree以戊宮室火度為始共360度
			Log.cf("PlanetPos(1) : " + Moira.mri.data.getPlanetPos(1, Moira.mri.result.birth_sign_pos[1]));
			Log.cf("PlanetPos(2) : " + Moira.mri.data.getPlanetPos(2, Moira.mri.result.birth_sign_pos[2]));
			Log.cf("PlanetPos(3) : " + Moira.mri.data.getPlanetPos(3, Moira.mri.result.birth_sign_pos[3]));
			Log.cf("PlanetPos(4) : " + Moira.mri.data.getPlanetPos(4, Moira.mri.result.birth_sign_pos[4]));
			Log.cf("PlanetPos(5) : " + Moira.mri.data.getPlanetPos(5, Moira.mri.result.birth_sign_pos[5]));
			Log.cf("PlanetPos(6) : " + Moira.mri.data.getPlanetPos(6, Moira.mri.result.birth_sign_pos[6]));
			Log.cf("PlanetPos(7) : " + Moira.mri.data.getPlanetPos(7, Moira.mri.result.birth_sign_pos[7]));
			Log.cf("PlanetPos(8) : " + Moira.mri.data.getPlanetPos(8, Moira.mri.result.birth_sign_pos[8]));
			Log.cf("PlanetPos(9) : " + Moira.mri.data.getPlanetPos(9, Moira.mri.result.birth_sign_pos[9]));
			Log.cf("PlanetPos(10) : " + Moira.mri.data.getPlanetPos(10, Moira.mri.result.birth_sign_pos[10]));
			Log.cf("PlanetPos(11) : " + Moira.mri.data.getPlanetPos(11, Moira.mri.result.birth_sign_pos[11]));
			Log.cf("PlanetPos(12) : " + Moira.mri.data.getPlanetPos(12, Moira.mri.result.birth_sign_pos[12]));
			Log.cf("PlanetPos(13) : " + Moira.mri.data.getPlanetPos(13, Moira.mri.result.birth_sign_pos[13]));
			Log.cf("PlanetPos(14) : " + Moira.mri.data.getPlanetPos(14, Moira.mri.result.birth_sign_pos[14]));
			Log.cf("PlanetPos(15) : " + Moira.mri.data.getPlanetPos(15, Moira.mri.result.birth_sign_pos[15]));
			Log.cf("PlanetPos(16) : " + Moira.mri.data.getPlanetPos(16, Moira.mri.result.birth_sign_pos[16]));
			Log.cf("PlanetPos(17) : " + Moira.mri.data.getPlanetPos(17, Moira.mri.result.birth_sign_pos[17]));
			Log.cf("PlanetPos(18) : " + Moira.mri.data.getPlanetPos(18, Moira.mri.result.birth_sign_pos[18]));
			Log.cf("PlanetPos(19) : " + Moira.mri.data.getPlanetPos(19, Moira.mri.result.birth_sign_pos[19]));
			Log.cf("PlanetPos(20) : " + Moira.mri.data.getPlanetPos(20, Moira.mri.result.birth_sign_pos[20]));
			Log.cf("PlanetPos(21) : " + Moira.mri.data.getPlanetPos(21, Moira.mri.result.birth_sign_pos[21]));
			Log.cf("PlanetPos of now");
			Log.cf("PlanetPos(0) : " + Moira.mri.data.getPlanetPos(0, Moira.mri.result.now_sign_pos[0])); // 第二個傳入參數degree以戊宮室火度為始共360度
			Log.cf("PlanetPos(1) : " + Moira.mri.data.getPlanetPos(1, Moira.mri.result.now_sign_pos[1]));
			Log.cf("PlanetPos(2) : " + Moira.mri.data.getPlanetPos(2, Moira.mri.result.now_sign_pos[2]));
			Log.cf("PlanetPos(3) : " + Moira.mri.data.getPlanetPos(3, Moira.mri.result.now_sign_pos[3]));
			Log.cf("PlanetPos(4) : " + Moira.mri.data.getPlanetPos(4, Moira.mri.result.now_sign_pos[4]));
			Log.cf("PlanetPos(5) : " + Moira.mri.data.getPlanetPos(5, Moira.mri.result.now_sign_pos[5]));
			Log.cf("PlanetPos(6) : " + Moira.mri.data.getPlanetPos(6, Moira.mri.result.now_sign_pos[6]));
			Log.cf("PlanetPos(7) : " + Moira.mri.data.getPlanetPos(7, Moira.mri.result.now_sign_pos[7]));
			Log.cf("PlanetPos(8) : " + Moira.mri.data.getPlanetPos(8, Moira.mri.result.now_sign_pos[8]));
			Log.cf("PlanetPos(9) : " + Moira.mri.data.getPlanetPos(9, Moira.mri.result.now_sign_pos[9]));
			Log.cf("PlanetPos(10) : " + Moira.mri.data.getPlanetPos(10, Moira.mri.result.now_sign_pos[10]));
			Log.cf("PlanetPos(11) : " + Moira.mri.data.getPlanetPos(11, Moira.mri.result.now_sign_pos[11]));
			Log.cf("PlanetPos(12) : " + Moira.mri.data.getPlanetPos(12, Moira.mri.result.now_sign_pos[12]));
			Log.cf("PlanetPos(13) : " + Moira.mri.data.getPlanetPos(13, Moira.mri.result.now_sign_pos[13]));
			Log.cf("PlanetPos(14) : " + Moira.mri.data.getPlanetPos(14, Moira.mri.result.now_sign_pos[14]));
			Log.cf("PlanetPos(15) : " + Moira.mri.data.getPlanetPos(15, Moira.mri.result.now_sign_pos[15]));
			Log.cf("PlanetPos(16) : " + Moira.mri.data.getPlanetPos(16, Moira.mri.result.now_sign_pos[16]));
			Log.cf("PlanetPos(17) : " + Moira.mri.data.getPlanetPos(17, Moira.mri.result.now_sign_pos[17]));
			Log.cf("PlanetPos(18) : " + Moira.mri.data.getPlanetPos(18, Moira.mri.result.now_sign_pos[18]));
			Log.cf("PlanetPos(19) : " + Moira.mri.data.getPlanetPos(19, Moira.mri.result.now_sign_pos[19]));
			Log.cf("PlanetPos(20) : " + Moira.mri.data.getPlanetPos(20, Moira.mri.result.now_sign_pos[20]));
			Log.cf("PlanetPos(21) : " + Moira.mri.data.getPlanetPos(21, Moira.mri.result.now_sign_pos[21]));
			Log.cf("getYearInfo() : " + Moira.mri.data.getYearInfo(true, Moira.mri.result.birth_table));
		}
		if (selector.contains("4")) {
			Log.cf("PoleDateData : " + Arrays.toString(Moira.mri.data.getPoleDateData(1984, "甲子")));
			Log.cf("ShowAspects : " + Moira.mri.data.getShowAspects());
			Log.cf("ShowFixstar : " + Moira.mri.data.getShowFixstar());
			Log.cf("ShowGauquelin : " + Moira.mri.data.getShowGauquelin());
			Log.cf("ShowHoriz : " + Moira.mri.data.getShowHoriz());
			Log.cf("ShowNow : " + Moira.mri.data.getShowNow());
			Log.cf("SmallLimit(小限@1歲) : " + Moira.mri.data.getSmallLimit(1, ","));
			Log.cf("SolidHouse : " + Moira.mri.data.getSolidHouse("子"));
			Log.cf("SearchResult : "
					+ Arrays.toString(Moira.mri.data.getSearchResult("台灣", "台北", "Asia/Taipei", "mode_name", true)));
			Log.cf("SignArray(中西諸星) : " + Arrays.toString(Moira.mri.data.getSignArray()));
			Log.cf("SignDegreeShiftArray : " + Arrays.toString(Moira.mri.data.getSignDegreeShiftArray()));
			Log.cf("SignDisplayArray : " + Arrays.toString(Moira.mri.data.getSignDisplayArray()));
			Log.cf("SignRevFlipArray : " + Arrays.toString(Moira.mri.data.getSignRevFlipArray()));
			Log.cf("SpeedColorArray : " + Arrays.toString(Moira.mri.data.getSpeedColorArray(false)));
			Log.cf("StateColorArray : " + Arrays.toString(Moira.mri.data.getStateColorArray()));
			Log.cf("TransitData : "
					+ Arrays.toString(Moira.mri.data.getTransitData(null, "台灣", "台北", "Asia/Taipei", 0)));
			Log.cf("WeakHouse : " + Moira.mri.data.getWeakHouse("子"));
			Log.cf("WallTime : " + Moira.mri.data.getWallTime(0, "台灣", "台北", "Asia/Taipei"));
			Log.cf("YearInfo(值年神煞) : " + Moira.mri.data.getYearInfo(true, new Hashtable()));
			Log.cf("\n以下是ChartData的Private參數\n============================================================");
			Log.cf("birth_sign_pos : " + Arrays.toString(Moira.mri.result.birth_sign_pos)); // birth_sign_pos[planet_no]
		}
		if (selector.contains("5")) {
			Log.cf("alt_pole_data  : " + Arrays.toString(Moira.mri.result.alt_pole_data));
			Log.cf("alt_year_data  : " + Arrays.toString(Moira.mri.result.alt_year_data));
			Log.cf("aspects_color  : " + Arrays.toString(Moira.mri.result.aspects_color));
			Log.cf("aspects_degree  : " + Arrays.toString(Moira.mri.result.aspects_degree));
			Log.cf("aspects_display  : " + Arrays.toString(Moira.mri.result.aspects_display));
			Log.cf("aspects_index  : " + Arrays.toString(Moira.mri.result.aspects_index));
			Log.cf("aspects_orb  : " + Arrays.toString(Moira.mri.result.aspects_orb));
			Log.cf("aspects_sign  : " + Arrays.toString(Moira.mri.result.aspects_sign));
			Log.cf("aspects_style  : " + Arrays.toString(Moira.mri.result.aspects_style));
			Log.cf("astro_elemental  : " + Arrays.toString(Moira.mri.result.astro_elemental));
			Log.cf("astro_elemental_state  : " + Arrays.toString(Moira.mri.result.astro_elemental_state));
			Log.cf("astro_sign_data  : " + Arrays.toString(Moira.mri.result.astro_sign_data));
			Log.cf("birth_adj_date  : " + Arrays.toString(Moira.mri.result.birth_adj_date));
			Log.cf("birth_cusp  : " + Arrays.toString(Moira.mri.result.birth_cusp));
			Log.cf("birth_cusp_override  : " + Arrays.toString(Moira.mri.result.birth_cusp_override));
			Log.cf("birth_dst_date  : " + Arrays.toString(Moira.mri.result.birth_dst_date));
			Log.cf("birth_loc  : " + Arrays.toString(Moira.mri.result.birth_loc));	//經緯
			Log.cf("birth_poles  : " + Arrays.toString(Moira.mri.result.birth_poles));	//[?,?,年柱,月柱,日柱,時柱]
			Log.cf("birth_rise_time  : " + Arrays.toString(Moira.mri.result.birth_rise_time));	//真太陽日出
			Log.cf("birth_set_time  : " + Arrays.toString(Moira.mri.result.birth_set_time));	//真太陽日落
			Log.cf("birth_sign_alt  : " + Arrays.toString(Moira.mri.result.birth_sign_alt));
			Log.cf("birth_sign_azimuth  : " + Arrays.toString(Moira.mri.result.birth_sign_azimuth));
			Log.cf("birth_sign_diameter  : " + Arrays.toString(Moira.mri.result.birth_sign_diameter));
			Log.cf("birth_sign_display_sort_orders  : " + Arrays.toString(Moira.mri.result.birth_sign_display_sort_orders));
			Log.cf("birth_sign_pos  : " + Arrays.toString(Moira.mri.result.birth_sign_pos));
			Log.cf("birth_sign_state  : " + Arrays.toString(Moira.mri.result.birth_sign_state));
			Log.cf("birth_sign_status  : " + Arrays.toString(Moira.mri.result.birth_sign_status));	//殿垣廟樂
			Log.cf("birth_speed_color  : " + Arrays.toString(Moira.mri.result.birth_speed_color));	//遲留伏逆	
			Log.cf("child_seq  : " + Arrays.toString(Moira.mri.result.child_seq));	//童限
			Log.cf("chinese_zodiac_signs  : " + Arrays.toString(Moira.mri.result.chinese_zodiac_signs));
			Log.cf("compass_sign_pos  : " + Arrays.toString(Moira.mri.result.compass_sign_pos));
			Log.cf("compass_stellar_names  : " + Arrays.toString(Moira.mri.result.compass_stellar_names));
			Log.cf("cusps_name  : " + Arrays.toString(Moira.mri.result.cusps_name));	//命財兄田男奴夫疾遷官福相
			Log.cf("day_pole_base  : " + Arrays.toString(Moira.mri.result.day_pole_base));
			Log.cf("day_pole_long_life_seq  : " + Arrays.toString(Moira.mri.result.day_pole_long_life_seq));
			Log.cf("draw_size  : " + Arrays.toString(Moira.mri.result.draw_size));
			Log.cf("earth_god_seq  : " + Arrays.toString(Moira.mri.result.earth_god_seq));
			Log.cf("earth_pole_names  : " + Arrays.toString(Moira.mri.result.earth_pole_names));	//地支名
			Log.cf("eight_char_override  : " + Arrays.toString(Moira.mri.result.eight_char_override));
			Log.cf("equator_rise_set  : " + Arrays.toString(Moira.mri.result.equator_rise_set));
			Log.cf("fixstar_names  : " + Arrays.toString(Moira.mri.result.fixstar_names));
			Log.cf("fixstar_sign_alt  : " + Arrays.toString(Moira.mri.result.fixstar_sign_alt));
			Log.cf("fixstar_sign_azimuth  : " + Arrays.toString(Moira.mri.result.fixstar_sign_azimuth));
			Log.cf("fixstar_sign_pos  : " + Arrays.toString(Moira.mri.result.fixstar_sign_pos));
			Log.cf("fixstar_sign_state  : " + Arrays.toString(Moira.mri.result.fixstar_sign_state));
			Log.cf("fixstar_signs  : " + Arrays.toString(Moira.mri.result.fixstar_signs));
			Log.cf("fly_seq_half_shift  : " + Arrays.toString(Moira.mri.result.fly_seq_half_shift));
			Log.cf("fly_seq_yang1  : " + Arrays.toString(Moira.mri.result.fly_seq_yang1));
			Log.cf("fly_seq_yang2  : " + Arrays.toString(Moira.mri.result.fly_seq_yang2));
			Log.cf("fly_seq_ying1  : " + Arrays.toString(Moira.mri.result.fly_seq_ying1));
			Log.cf("fly_seq_ying2  : " + Arrays.toString(Moira.mri.result.fly_seq_ying2));
			Log.cf("full_mountain_main_signs  : " + Arrays.toString(Moira.mri.result.full_mountain_main_signs));
			Log.cf("full_mountain_secondary_signs  : " + Arrays.toString(Moira.mri.result.full_mountain_secondary_signs));
			Log.cf("full_mountain_signs  : " + Arrays.toString(Moira.mri.result.full_mountain_signs));
			Log.cf("full_stellar_signs  : " + Arrays.toString(Moira.mri.result.full_stellar_signs));
			Log.cf("full_twelve_signs  : " + Arrays.toString(Moira.mri.result.full_twelve_signs));
			Log.cf("full_zodiac  : " + Arrays.toString(Moira.mri.result.full_zodiac));
			Log.cf("gauquelin_sign_pos  : " + Arrays.toString(Moira.mri.result.gauquelin_sign_pos));
			Log.cf("gauquelin_sign_region  : " + Arrays.toString(Moira.mri.result.gauquelin_sign_region));
			Log.cf("half_stellar_signs  : " + Arrays.toString(Moira.mri.result.half_stellar_signs));
			Log.cf("hour_sky_pole_shifts  : " + Arrays.toString(Moira.mri.result.hour_sky_pole_shifts));
			Log.cf("house_name  : " + Arrays.toString(Moira.mri.result.house_name));
			Log.cf("limit_seq  : " + Arrays.toString(Moira.mri.result.limit_seq));	//12宮管限年數
			Log.cf("long_life_signs  : " + Arrays.toString(Moira.mri.result.long_life_signs));	//12長生
			Log.cf("lunar_date  : " + Arrays.toString(Moira.mri.result.lunar_date));
			Log.cf("lunar_transits  : " + Arrays.toString(Moira.mri.result.lunar_transits));
			Log.cf("month_sky_pole_shifts  : " + Arrays.toString(Moira.mri.result.month_sky_pole_shifts));
			Log.cf("mountain_signs  : " + Arrays.toString(Moira.mri.result.mountain_signs));
			Log.cf("new_moons  : " + Arrays.toString(Moira.mri.result.new_moons));
			Log.cf("now_adj_date  : " + Arrays.toString(Moira.mri.result.now_adj_date));
			Log.cf("now_cusp  : " + Arrays.toString(Moira.mri.result.now_cusp));
			Log.cf("now_dst_date  : " + Arrays.toString(Moira.mri.result.now_dst_date));
			Log.cf("now_loc  : " + Arrays.toString(Moira.mri.result.now_loc));
			Log.cf("now_lunar_date  : " + Arrays.toString(Moira.mri.result.now_lunar_date));
			Log.cf("now_poles  : " + Arrays.toString(Moira.mri.result.now_poles));
			Log.cf("now_sign_alt  : " + Arrays.toString(Moira.mri.result.now_sign_alt));
			Log.cf("now_sign_azimuth  : " + Arrays.toString(Moira.mri.result.now_sign_azimuth));
			Log.cf("now_sign_display_sort_orders  : " + Arrays.toString(Moira.mri.result.now_sign_display_sort_orders));
			Log.cf("now_sign_pos  : " + Arrays.toString(Moira.mri.result.now_sign_pos));
			Log.cf("now_sign_state  : " + Arrays.toString(Moira.mri.result.now_sign_state));
			Log.cf("now_sign_status  : " + Arrays.toString(Moira.mri.result.now_sign_status));	//殿垣廟樂
			Log.cf("now_speed_color  : " + Arrays.toString(Moira.mri.result.now_speed_color));	//遲留伏逆
			Log.cf("now_state_color  : " + Arrays.toString(Moira.mri.result.now_state_color));
			Log.cf("now_year_seq  : " + Arrays.toString(Moira.mri.result.now_year_seq));
			Log.cf("planets  : " + Arrays.toString(Moira.mri.result.planets));
			Log.cf("power_index  : " + Arrays.toString(Moira.mri.result.power_index));
			Log.cf("power_key  : " + Arrays.toString(Moira.mri.result.power_key));
			Log.cf("season_starts  : " + Arrays.toString(Moira.mri.result.season_starts));	//24節氣
			Log.cf("sidereal_stellar_names  : " + Arrays.toString(Moira.mri.result.sidereal_stellar_names));	
			Log.cf("sign_computation_type  : " + Arrays.toString(Moira.mri.result.sign_computation_type));
			Log.cf("sign_display  : " + Arrays.toString(Moira.mri.result.sign_display));
			Log.cf("sign_display_orders  : " + Arrays.toString(Moira.mri.result.sign_display_orders));
			Log.cf("sign_lock  : " + Arrays.toString(Moira.mri.result.sign_lock));
			Log.cf("sign_opposite  : " + Arrays.toString(Moira.mri.result.sign_opposite));
			Log.cf("sign_pos_shift  : " + Arrays.toString(Moira.mri.result.sign_pos_shift));
			Log.cf("sign_prefix  : " + Arrays.toString(Moira.mri.result.sign_prefix));
			Log.cf("sign_rev_flip  : " + Arrays.toString(Moira.mri.result.sign_rev_flip));
			Log.cf("signs  : " + Arrays.toString(Moira.mri.result.signs));	//七政四餘&西洋諸星名
			Log.cf("sky_pole_names  : " + Arrays.toString(Moira.mri.result.sky_pole_names));	//天干名
			Log.cf("solar_date  : " + Arrays.toString(Moira.mri.result.solar_date));
			Log.cf("solar_terms  : " + Arrays.toString(Moira.mri.result.solar_terms));
			Log.cf("solid_house  : " + Arrays.toString(Moira.mri.result.solid_house));
			Log.cf("star_equ_map  : " + Arrays.toString(Moira.mri.result.star_equ_map));
			Log.cf("stellar_names  : " + Arrays.toString(Moira.mri.result.stellar_names));
			Log.cf("stellar_sign_pos  : " + Arrays.toString(Moira.mri.result.stellar_sign_pos));		//28宿起度
			Log.cf("ten_god_seq1  : " + Arrays.toString(Moira.mri.result.ten_god_seq1));
			Log.cf("ten_god_seq2  : " + Arrays.toString(Moira.mri.result.ten_god_seq2));
			Log.cf("three_danger  : " + Arrays.toString(Moira.mri.result.three_danger));
			Log.cf("twelve_signs  : " + Arrays.toString(Moira.mri.result.twelve_signs));
			Log.cf("weak_house  : " + Arrays.toString(Moira.mri.result.weak_house));
			Log.cf("wife_signs  : " + Arrays.toString(Moira.mri.result.wife_signs));
			Log.cf("year_data  : " + Arrays.toString(Moira.mri.result.year_data));
			Log.cf("year_names  : " + Arrays.toString(Moira.mri.result.year_names));	//六十甲子名
			Log.cf("year_signs  : " + Arrays.toString(Moira.mri.result.year_signs));	//命相福官遷疾夫奴男田兄財
			Log.cf("year_star_map  : " + Arrays.toString(Moira.mri.result.year_star_map));
			Log.cf("year_star_range  : " + Arrays.toString(Moira.mri.result.year_star_range));
			Log.cf("year_star_seq  : " + Arrays.toString(Moira.mri.result.year_star_seq));
		}
		Log.CloseLogFile();
	}
	
	private void testCrossHouseFullYear(int year, boolean test7M) {
		int y_days = (year % 4 == 0) ? 366 : 365;
		int[] crov = new int[5];
		int p0 = 0;
		int pl = 0;
		if (test7M) {
			p0 = 0;
			pl = 6;
		} else {
			p0 = 12; // 羅計會有問題，跳過不測
			pl = 13;
		}

		for (int p = p0; p <= pl; p += 1) {
			int[] start = { year, 1, 1, 9, 0 };
			crov = testCrossHouse(p, start, 1, y_days);
			if (crov[0] < 0 || crov[1] < 0)
				break;
		}
	}

	private int[] testCrossHouse(int planet_no, int[] start, int span, int repeats) {
		int[] crossOver = new int[5];
		Log.c("Test Cross House");
		Calendar cal_start = Calendar.getInstance();
		// cal_start.set(start[0], start[1] - 1, start[2], start[3], start[4]);
		MRI.setCalendar(cal_start, start);
		for (int i = 0; i < repeats * span; i += span) {
			// start[0] = cal_start.get(Calendar.YEAR);
			// start[1] = cal_start.get(Calendar.MONTH) + 1; // Jan = 0
			// start[2] = cal_start.get(Calendar.DAY_OF_MONTH);
			// start[3] = cal_start.get(Calendar.HOUR_OF_DAY);
			// start[4] = cal_start.get(Calendar.MINUTE);
			MRI.getCalendar(cal_start, start);
			Moira.mri.setNowTime(start);
			Moira.run();
			Log.c("Test Date: " + Arrays.toString(start));
			crossOver = Moira.mri.getHouseCrossOver(planet_no, Moira.mri.result.now_sign_pos[planet_no], start);
			Log.c(Arrays.toString(crossOver));
			if (crossOver[0] < 0 || crossOver[1] < 0)
				return crossOver;
			cal_start.add(Calendar.DAY_OF_MONTH, span);
		}
		Log.c("Test Completed");
		return crossOver;
	}	
}


