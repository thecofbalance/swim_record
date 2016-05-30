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
		
		//�n�b�V���쐬
		
		year.put("2007","07");
		year.put("2008","08");
		year.put("2009","09");
		year.put("2010","10");
		year.put("2011","11");
		year.put("2012","12");
		year.put("2013","13");
		year.put("2014","14");
		year.put("2015","15");
		
		place.put("�k�C��","01");
		place.put("�X","02");
		place.put("���","03");
		place.put("�{��","04");
		place.put("�H�c","05");
		place.put("�R�`","06");
		place.put("����","07");
		place.put("���","08");
		place.put("�Ȗ�","09");
		place.put("�Q�n","10");
		place.put("���","11");
		place.put("��t","12");
		place.put("����","13");
		place.put("�_�ސ�","14");
		place.put("�R��","15");
		place.put("����","16");
		place.put("�V��","17");
		place.put("�x�R","18");
		place.put("�ΐ�","19");
		place.put("����","20");
		place.put("�É�","21");
		place.put("���m","22");
		place.put("�O�d","23");
		place.put("��","24");
		place.put("����","25");
		place.put("���s","26");
		place.put("���","27");
		place.put("����","28");
		place.put("�ޗ�","29");
		place.put("�a�̎R","30");
		place.put("����","31");
		place.put("����","32");
		place.put("���R","33");
		place.put("�L��","34");
		place.put("�R��","35");
		place.put("����","36");
		place.put("����","37");
		place.put("���Q","38");
		place.put("���m","39");
		place.put("����","40");
		place.put("����","41");
		place.put("����","42");
		place.put("�F�{","43");
		place.put("�啪","44");
		place.put("�{��","45");
		place.put("������","46");
		place.put("����","47");
		place.put("�w�A�֓�","48");
		place.put("�w�A����","49");
		place.put("�w�A�֐�","50");
		place.put("�w�A���E�l��","51");
		place.put("�w�A��B","52");
		place.put("�w�A�k��","53");
		place.put("�S�����","70");
		place.put("���ۑ��","80");
		
		
		//�X�e�[�g�}�V��
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
				System.out.println("���������, e : end");
				System.out.println();
				input = new BufferedReader (new InputStreamReader (System.in));
			    placeIn = input.readLine( );
			    
			    if(placeIn.equals("e"))
			    {
				    state = -1;
			    }
			    else
			    {
			    	System.out.println("�J�ÔN�����(�J�ÔN��2007�`2015)");
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
				    	System.out.println("���͂��Ԉ���Ă��܂�(�J�ÔN��2007�`2014)");
				    }
			    }
			    
			}
			
			if(state == 2)
			{
				table = createMeetList.createTable(br);
				createMeetList.tablePrinter(true);
//				table = createTableAt_2(br);
//				tablePrinter(table, true);
				System.out.println("����ԍ��őI��,b:�߂� ");
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
				    	System.out.println("���͂��Ԉ���Ă��܂�");
				    }
				}
				
				
			}
			
			if(state == 3)
			{
				table = createTableAt_3(br);
				table.remove(table.size() - 1);
				tablePrinter(table, true);
				System.out.println("��ڔԍ��I��,b:�߂� ");
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
				    	System.out.println("���͂��Ԉ���Ă��܂�");
				    	
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
			
			if(state == -1)System.out.println("�I��");
			
		}
		
		
	}
	
	
	
	
	
	//br�쐬
	private static BufferedReader createBr(String urlString) throws Exception
	{
		// URL���쐬����B
		URL url = new java.net.URL(urlString);

		// �ڑ����擾���� (�ڑ��� new ���č��̂ł͂Ȃ��AopenConnection ���\�b�h�Ŏ擾����)�B
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET"); // �������͔C�ӁB�Ȃ��Ă��ǂ�

		// ���[�_�[��ǂ��HTTP���X�|���X���擾����B
		// �������C���N�G�X�g�������URL���摜�Ȃǂ̏ꍇ�́AInputStream�Ńo�C�g��Ƃ��Ĉ����B
		InputStreamReader isr = new java.io.InputStreamReader(conn.getInputStream(), "SJIS");
		BufferedReader br = new java.io.BufferedReader(isr);
		
		return br;
	}
	
	
	
	
	
	//���2���ʕ\���̕\����
	private static List<List<StringBuffer>> createTableAt_2(BufferedReader br) throws Exception
	{
		// ��M�����X�g���[����\��
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
			
			//�e�[�u����3�ڂ���A�܂��e�[�u�����e�[�u���͋����Ȃ�
			if(cntTable == 1 && gapOfTableEnd == 1 && line.length() != 0)
			{
				linesHtml_1.add(new StringBuffer(line));
			}
			
			if(line.endsWith("</table>")) cntTableEnd ++;
		}
		
		//HTML���H���H��
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
		
		//HTML���H���H���A�i�[
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
					//����
					if(cntColumn == 1) 
					{
						if(tmp.indexOf("<br />") != -1) tmp = tmp.delete(tmp.indexOf("<br />"), tmp.indexOf("<br />") + 6);
					}
					
					//���/��ꖼ
					if(cntColumn == 2) 
					{
						tmp = tmp.insert(tmp.indexOf("place\">") + 7, "/");
						tmp = tmp.delete(tmp.indexOf("<a name"), tmp.indexOf("place\">") + 7);
						tmp = tmp.delete(tmp.indexOf("</div>"), tmp.indexOf("</div>") + 6);
					}
					
					//���H
					if(cntColumn == 3) 
					{
						if(tmp.indexOf("short") != -1) tmp = new StringBuffer("short");
						else tmp = new StringBuffer("long");
					}
					
					//��ڑI����ʃA�h���X
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
		
//		//���H�����j�^�[
//		for(StringBuffer tmp : linesHtml_2)
//		{
//			System.out.println(tmp.toString());
//		}
		
	}
	
	
	
	
	
	//���3���ʕ\���̕\����
	private static List<List<StringBuffer>> createTableAt_3(BufferedReader br) throws Exception
	{
		// ��M�����X�g���[����\��
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
			
			//�e�[�u����3�ڂ���A�܂��e�[�u�����e�[�u���͋����Ȃ�
			if(cntTable == 3 && gapOfTableEnd == 1 && line.length() != 0)
			{
				linesHtml_1.add(new StringBuffer(line));
			}
			
			if(line.endsWith("</table>")) cntTableEnd ++;
		}
		
		//HTML���H���H��
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
		
		
		//HTML���H���H���A�i�[
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
		
//		//���H�����j�^�[
//		for(StringBuffer tmp : linesHtml_2)
//		{
//			System.out.println(tmp.toString());
//		}
				
	}
	
	
	
	
	
	//���4���ʕ\���̕\����
	private static List<List<StringBuffer>> createTableAt_4(BufferedReader br) throws Exception
	{
		// ��M�����X�g���[����\��
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
			
			//�e�[�u����3�ڂ���A�܂��e�[�u�����e�[�u���͋����Ȃ�
			if(cntTable >= 3 && gapOfTableEnd == 1)
			{
				linesHtml_1.add(new StringBuffer(line));
			}
			
			if(line.endsWith("</table>")) cntTableEnd ++;
		}

		//HTML���H���H��
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
		
		//HTML���H���H���A�i�[
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
		
//		//���H�����j�^�[
//		for(StringBuffer tmp : linesHtml_2)
//		{
//			System.out.println(tmp.toString());
//		}
	}
	
	
	
	
	
	//�\�̕\��
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
