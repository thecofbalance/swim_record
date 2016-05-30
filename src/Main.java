import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		
		List<List<StringBuffer>> table;
		HashMap<String,String> year = new HashMap<String,String>();
		HashMap<String,String> place = new HashMap<String,String>();
		
		CreateMeetList createMeetList = new CreateMeetList();
		
		String homeUrl = "http://www.swim-record.com";
		
		//ハッシュ作成
		
		year.put("2007","07");
		year.put("2008","08");
		year.put("2009","09");
		year.put("2010","10");
		year.put("2011","11");
		year.put("2012","12");
		year.put("2013","13");
		year.put("2014","14");
		year.put("2015","15");
		
		place.put("北海道","01");
		place.put("青森","02");
		place.put("岩手","03");
		place.put("宮城","04");
		place.put("秋田","05");
		place.put("山形","06");
		place.put("福島","07");
		place.put("茨城","08");
		place.put("栃木","09");
		place.put("群馬","10");
		place.put("埼玉","11");
		place.put("千葉","12");
		place.put("東京","13");
		place.put("神奈川","14");
		place.put("山梨","15");
		place.put("長野","16");
		place.put("新潟","17");
		place.put("富山","18");
		place.put("石川","19");
		place.put("福井","20");
		place.put("静岡","21");
		place.put("愛知","22");
		place.put("三重","23");
		place.put("岐阜","24");
		place.put("滋賀","25");
		place.put("京都","26");
		place.put("大阪","27");
		place.put("兵庫","28");
		place.put("奈良","29");
		place.put("和歌山","30");
		place.put("鳥取","31");
		place.put("島根","32");
		place.put("岡山","33");
		place.put("広島","34");
		place.put("山口","35");
		place.put("香川","36");
		place.put("徳島","37");
		place.put("愛媛","38");
		place.put("高知","39");
		place.put("福岡","40");
		place.put("佐賀","41");
		place.put("長崎","42");
		place.put("熊本","43");
		place.put("大分","44");
		place.put("宮崎","45");
		place.put("鹿児島","46");
		place.put("沖縄","47");
		place.put("学連関東","48");
		place.put("学連中部","49");
		place.put("学連関西","50");
		place.put("学連中・四国","51");
		place.put("学連九州","52");
		place.put("学連北部","53");
		place.put("全国大会","70");
		place.put("国際大会","80");
		
		
		//ステートマシン
		int state = 1;

		BufferedReader input;
		BufferedReader br = null;
		
		String yearIn;
		String placeIn;
		
		String resultIn = null;
		
		while(state != -1)
		{
			if(state == 1)
			{
				System.out.println("県名を入力, e : end");
				System.out.println();
				input = new BufferedReader (new InputStreamReader (System.in));
			    placeIn = input.readLine( );
			    
			    if(placeIn.equals("e"))
			    {
				    state = -1;
			    }
			    else
			    {
			    	System.out.println("開催年を入力(開催年は2007～2015)");
				    System.out.println();
					input = new BufferedReader (new InputStreamReader (System.in, "UTF-8"));
				    yearIn = input.readLine( );
				    
				    if(year.get(yearIn) != null && place.get(placeIn) != null)
				    {
				    	br = createBr(homeUrl + "/taikai/" + year.get(yearIn) + "/" + place.get(placeIn) + ".html");
				    	state = 2;
				    	System.out.println(homeUrl + "/taikai/" + year.get(yearIn) + "/" + place.get(placeIn) + ".html");
				    }
				    else
				    {
				    	System.out.println("入力が間違っています(開催年は2007～2014)");
				    }
			    }
			    
			}
			
			if(state == 2)
			{
				table = createMeetList.createTable(br);
				createMeetList.tablePrinter(true);
//				table = createTableAt_2(br);
//				tablePrinter(table, true);
				System.out.println("大会を番号で選択,b:戻る ");
				input = new BufferedReader (new InputStreamReader (System.in, "UTF-8"));
				resultIn = input.readLine();
				
				if(resultIn.equals("b"))
				{
					state = 1;
				}
				else
				{
					if(Integer.parseInt(resultIn) < table.size())
				    {
						br = createBr(homeUrl + table.get(Integer.parseInt(resultIn)).get(3));
						System.out.println(homeUrl + table.get(Integer.parseInt(resultIn)).get(3));
				    	state = 3;
				    }
				    else
				    {
				    	System.out.println("入力が間違っています");
				    }
				}
				
				
			}
			
			if(state == 3)
			{
				table = createTableAt_3(br);
				table.remove(table.size() - 1);
				tablePrinter(table, true);
				System.out.println("種目番号選択,b:戻る ");
				input = new BufferedReader (new InputStreamReader (System.in, "UTF-8"));
				resultIn = input.readLine();
				
				if(resultIn.equals("b"))
				{
					state = 1;
				}
				else
				{
					if(Integer.parseInt(resultIn) < table.size())
				    {
						br = createBr(homeUrl + table.get(Integer.parseInt(resultIn)).get(0));
						System.out.println(homeUrl + table.get(Integer.parseInt(resultIn)).get(0));
				    	state = 4;
				    }
				    else
				    {
				    	System.out.println("入力が間違っています");
				    	
				    }
				}
			}
			
			if(state == 4)
			{
				table = createTableAt_4(br);
				table.remove(table.size() - 1);
				tablePrinter(table, false);
				state = 1;
			}
			
			if(state == -1)System.out.println("終了");
			
		}
		
		
	}
	
	
	
	
	
	//br作成
	private static BufferedReader createBr(String urlString) throws Exception
	{
		// URLを作成する。
		URL url = new java.net.URL(urlString);

		// 接続を取得する (接続は new して作るのではなく、openConnection メソッドで取得する)。
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET"); // ←ここは任意。なくても良い

		// リーダーを読んでHTTPレスポンスを取得する。
		// ただし，リクエストした先のURLが画像などの場合は、InputStreamでバイト列として扱う。
		InputStreamReader isr = new java.io.InputStreamReader(conn.getInputStream(), "SJIS");
		BufferedReader br = new java.io.BufferedReader(isr);
		
		return br;
	}
	
	
	
	
	
	//画面2結果表示の表製作
	private static List<List<StringBuffer>> createTableAt_2(BufferedReader br) throws Exception
	{
		// 受信したストリームを表示
		List<StringBuffer> linesHtml_1 = new ArrayList<StringBuffer>();
		List<StringBuffer> linesHtml_2 = new ArrayList<StringBuffer>();
		List<List<StringBuffer>> table = new ArrayList<List<StringBuffer>>();
		String line = null;
		
		int cntTable = 0;
		int cntTableEnd = 0;
		int gapOfTableEnd = 0;
		
		List<StringBuffer> column = null;
		
		while (null != (line = br.readLine()))
		{
			line = line.trim();
			if(line.startsWith("<table")) cntTable ++;
			gapOfTableEnd = cntTable - cntTableEnd;
			
			//テーブルは3つ目から、またテーブル内テーブルは許さない
			if(cntTable == 1 && gapOfTableEnd == 1 && line.length() != 0)
			{
				linesHtml_1.add(new StringBuffer(line));
			}
			
			if(line.endsWith("</table>")) cntTableEnd ++;
		}
		
		//HTML加工第一工程
		for(StringBuffer tmp : linesHtml_1)
		{
			
			if(tmp.toString().endsWith("</table>"))
			{
				tmp = tmp.delete(tmp.indexOf("</table>"), tmp.indexOf("</table>") + 8);
			}
			
			if(tmp.toString().endsWith("</tr>"))
			{
				tmp = tmp.delete(tmp.indexOf("</tr>"), tmp.indexOf("</tr>") + 5);
			}
			
			if(tmp.toString().endsWith("</td>"))
			{
				tmp = tmp.delete(tmp.indexOf("</td>"), tmp.indexOf("</td>") + 5);
			}
			
			if(tmp.toString().startsWith("<table"))
			{
				tmp = tmp.delete(0, tmp.indexOf(">") + 1);
				linesHtml_2.add(new StringBuffer("<table>"));
			}
			
			if(tmp.toString().startsWith("<tr"))
			{
				tmp = tmp.delete(0, tmp.indexOf(">") + 1);
				linesHtml_2.add(new StringBuffer("<tr>"));
			}
			
			if(tmp.toString().startsWith("<td"))
			{
				tmp = tmp.delete(0, tmp.indexOf(">") + 1);
				//linesHtml_2.add(new StringBuffer("<td>"));
			}
			else
			{
				tmp = linesHtml_2.get(linesHtml_2.size() - 1).append(tmp.toString());
				linesHtml_2.remove(linesHtml_2.get(linesHtml_2.size() - 1));
			}
			
			if(tmp.length() != 0)
			{
				linesHtml_2.add(tmp);
			}
			
		}
		
		int cntRow = 0;
		int cntColumn = 0;
		
		//HTML加工第二工程、格納
		for(StringBuffer tmp : linesHtml_2)
		{
			if(tmp.toString().startsWith("<table")){}
			else if(tmp.toString().startsWith("<tr"))
			{
				cntRow++;
				cntColumn = 0;
				if(column != null && column.size() != 0) table.add(column);
				column = new ArrayList<StringBuffer>();
			}
			else
			{
				cntColumn ++;
				
				
				if(cntRow > 1)
				{
					//日程
					if(cntColumn == 1) 
					{
						if(tmp.indexOf("<br />") != -1) tmp = tmp.delete(tmp.indexOf("<br />"), tmp.indexOf("<br />") + 6);
					}
					
					//大会/会場名
					if(cntColumn == 2) 
					{
						tmp = tmp.insert(tmp.indexOf("place\">") + 7, "/");
						tmp = tmp.delete(tmp.indexOf("<a name"), tmp.indexOf("place\">") + 7);
						tmp = tmp.delete(tmp.indexOf("</div>"), tmp.indexOf("</div>") + 6);
					}
					
					//水路
					if(cntColumn == 3) 
					{
						if(tmp.indexOf("short") != -1) tmp = new StringBuffer("short");
						else tmp = new StringBuffer("long");
					}
					
					//種目選択画面アドレス
					if(cntColumn == 6) 
					{
						if(tmp.indexOf("ViewResult") != -1) 
						{
							tmp = tmp.delete(0, tmp.indexOf("/swims/"));
							tmp = tmp.delete(tmp.indexOf("target") -2 , tmp.length());
						}
					}
				}
				
				
				if(cntColumn <= 3 || cntColumn == 6) column.add(new StringBuffer(tmp.toString().trim()));
			}
		}
		
		table.add(column);
		return table;
		
//		//第一工程モニター
//		for(StringBuffer tmp : linesHtml_2)
//		{
//			System.out.println(tmp.toString());
//		}
		
	}
	
	
	
	
	
	//画面3結果表示の表製作
	private static List<List<StringBuffer>> createTableAt_3(BufferedReader br) throws Exception
	{
		// 受信したストリームを表示
		List<StringBuffer> linesHtml_1 = new ArrayList<StringBuffer>();
		List<StringBuffer> linesHtml_2 = new ArrayList<StringBuffer>();
		List<List<StringBuffer>> table = new ArrayList<List<StringBuffer>>();
		String line = null;
		
		int cntTable = 0;
		int cntTableEnd = 0;
		int gapOfTableEnd = 0;
		
		List<StringBuffer> column = null;
		
		while (null != (line = br.readLine()))
		{
			line = line.trim();
			if(line.startsWith("<table")) cntTable ++;
			gapOfTableEnd = cntTable - cntTableEnd;
			
			//テーブルは3つ目から、またテーブル内テーブルは許さない
			if(cntTable == 3 && gapOfTableEnd == 1 && line.length() != 0)
			{
				linesHtml_1.add(new StringBuffer(line));
			}
			
			if(line.endsWith("</table>")) cntTableEnd ++;
		}
		
		//HTML加工第一工程
		for(StringBuffer tmp : linesHtml_1)
		{
			
			if(tmp.toString().endsWith("</table>"))
			{
				tmp = tmp.delete(tmp.indexOf("</table>"), tmp.indexOf("</table>") + 8);
			}
			
			if(tmp.toString().endsWith("</tr>"))
			{
				tmp = tmp.delete(tmp.indexOf("</tr>"), tmp.indexOf("</tr>") + 5);
			}
			
			if(tmp.toString().endsWith("</td>"))
			{
				tmp = tmp.delete(tmp.indexOf("</td>"), tmp.indexOf("</td>") + 5);
			}
			
			if(tmp.toString().startsWith("<table"))
			{
				tmp = tmp.delete(0, tmp.indexOf(">") + 1);
				linesHtml_2.add(new StringBuffer("<table>"));
			}
			
			if(tmp.toString().startsWith("<tr"))
			{
				tmp = tmp.delete(0, tmp.indexOf(">") + 1);
				linesHtml_2.add(new StringBuffer("<tr>"));
			}
			
			if(tmp.toString().startsWith("<td"))
			{
				tmp = tmp.delete(tmp.indexOf("<td"), tmp.indexOf(">") + 1);
				//linesHtml_2.add(new StringBuffer("<td>"));
			}
//					else
//					{
//						tmp = linesHtml_2.get(linesHtml_2.size() - 1).append(tmp.toString());
//						linesHtml_2.remove(linesHtml_2.get(linesHtml_2.size() - 1));
//					}
			
			if(tmp.length() != 0)
			{
				linesHtml_2.add(tmp);
			}
			
		}
		
		
		//HTML加工第二工程、格納
		for(StringBuffer tmp : linesHtml_2)
		{
			
			if(tmp.toString().startsWith("<table")){}
			else if(tmp.toString().startsWith("<tr")){}
			else
			{
				if(column != null && column.size() != 0) table.add(column);
				column = new ArrayList<StringBuffer>();
				
				if(tmp.indexOf("<a") != -1)
				{
					tmp = tmp.delete(tmp.indexOf("<a"), tmp.indexOf("=") + 2);
					tmp = tmp.delete(tmp.indexOf("class") - 2, tmp.indexOf(">"));
					tmp = tmp.delete(tmp.length() - 4, tmp.length());
					
					column.add(new StringBuffer(tmp.toString()).delete(tmp.indexOf(">"), tmp.length()));
					column.add(tmp.delete(0, tmp.indexOf(">") + 1));

				}
				else if(tmp.indexOf("&nbsp;") != -1){}
				else column.add(tmp);
				
			}
		}
		
		table.add(column);
		
		return table;
		
//		//第一工程モニター
//		for(StringBuffer tmp : linesHtml_2)
//		{
//			System.out.println(tmp.toString());
//		}
				
	}
	
	
	
	
	
	//画面4結果表示の表製作
	private static List<List<StringBuffer>> createTableAt_4(BufferedReader br) throws Exception
	{
		// 受信したストリームを表示
		List<StringBuffer> linesHtml_1 = new ArrayList<StringBuffer>();
		List<StringBuffer> linesHtml_2 = new ArrayList<StringBuffer>();
		List<List<StringBuffer>> table = new ArrayList<List<StringBuffer>>();
		String line = null;
		
		int cntTable = 0;
		int cntTableEnd = 0;
		int gapOfTableEnd = 0;
		
		List<StringBuffer> column = null;
		
		while (null != (line = br.readLine()))
		{
			line = line.trim();
			if(line.startsWith("<table")) cntTable ++;
			gapOfTableEnd = cntTable - cntTableEnd;
			
			//テーブルは3つ目から、またテーブル内テーブルは許さない
			if(cntTable >= 3 && gapOfTableEnd == 1)
			{
				linesHtml_1.add(new StringBuffer(line));
			}
			
			if(line.endsWith("</table>")) cntTableEnd ++;
		}

		//HTML加工第一工程
		for(StringBuffer tmp : linesHtml_1)
		{
			
			if(tmp.toString().endsWith("</table>"))
			{
				tmp = tmp.delete(tmp.indexOf("</table>"), tmp.indexOf("</table>") + 8);
			}
			
			if(tmp.toString().endsWith("</tr>"))
			{
				tmp = tmp.delete(tmp.indexOf("</tr>"), tmp.indexOf("</tr>") + 5);
			}
			
			if(tmp.toString().endsWith("</td>"))
			{
				tmp = tmp.delete(tmp.indexOf("</td>"), tmp.indexOf("</td>") + 5);
			}
			
			if(tmp.toString().startsWith("<table"))
			{
				tmp = tmp.delete(0, tmp.indexOf(">") + 1);
				linesHtml_2.add(new StringBuffer("<table>"));
			}
			
			if(tmp.toString().startsWith("<tr"))
			{
				tmp = tmp.delete(0, tmp.indexOf(">") + 1);
				linesHtml_2.add(new StringBuffer("<tr>"));
			}
			
			if(tmp.toString().startsWith("<td"))
			{
				tmp = tmp.delete(0, tmp.indexOf(">") + 1);
				//linesHtml_2.add(new StringBuffer("<td>"));
			}
			
			if(tmp.length() != 0)
			{
				linesHtml_2.add(tmp);
			}
			
		}
		
		//HTML加工第二工程、格納
		for(StringBuffer tmp : linesHtml_2)
		{
			if(tmp.toString().startsWith("<table")){}
			else if(tmp.toString().startsWith("<tr"))
			{
				if(column != null && column.size() != 0) table.add(column);
				column = new ArrayList<StringBuffer>();
			}
			else
			{
				if(tmp.indexOf("&nbsp;") != -1) tmp = tmp.delete(tmp.indexOf("&nbsp;"), tmp.indexOf("&nbsp;") + 6);
				if(tmp.indexOf("<a") != -1) tmp = tmp.delete(tmp.indexOf("<a"), tmp.indexOf(">") + 1);
				if(tmp.indexOf("</a>") != -1) tmp = tmp.delete(tmp.indexOf("</a>"), tmp.indexOf("</a>") + 4);
				if(column.size() < 5) column.add(new StringBuffer(tmp.toString().trim()));
			}
		}
		
		table.add(column);
		return table;
		
//		//第一工程モニター
//		for(StringBuffer tmp : linesHtml_2)
//		{
//			System.out.println(tmp.toString());
//		}
	}
	
	
	
	
	
	//表の表示
	private static void tablePrinter(List<List<StringBuffer>> table, boolean num)
	{
		for(List<StringBuffer> row : table)
		{
			if(num)
			{
				System.out.println(table.indexOf(row));
			}
			for(StringBuffer colmun : row)
			{
				System.out.print(colmun.toString() + " ");
				
			}
		
			System.out.println();
			
		}
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

}
