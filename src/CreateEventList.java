import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CreateEventList extends TableParent
{
	@Override
	List<List<StringBuffer>> createTable(BufferedReader br) throws IOException
	{
		//URLのカラム番号設定
		setUrlColmunNumber(3);
		
		// 受信したストリームを表示
		List<StringBuffer> linesHtml_1 = new ArrayList<StringBuffer>();
		List<StringBuffer> linesHtml_2 = new ArrayList<StringBuffer>();
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
			
			//テーブルは1つ目、またテーブル内テーブルは許さない
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
	}
}
