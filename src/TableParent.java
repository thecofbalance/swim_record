import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


abstract class TableParent 
{
	public class CreateEventList {

	}


	protected List<List<StringBuffer>> table = new ArrayList<List<StringBuffer>>();
	protected int urlColmunNumber;
	
	
	public int getUrlColmunNumber() 
	{
		return urlColmunNumber;
	}

	
	public void setUrlColmunNumber(int urlColmunNumber) 
	{
		this.urlColmunNumber = urlColmunNumber;
	}
	
	
	//表の表示
	public void tablePrinter(boolean numFlag)
	{
		for(List<StringBuffer> row : table)
		{
			for(StringBuffer colmun : row)
			{
				if(row.indexOf(colmun) != urlColmunNumber)
				{
					System.out.print(colmun.toString() + " ");
				}
			}
			if(numFlag)
			{
				System.out.println(table.indexOf(row));
			}
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
	
	
	//各子画面クラスで表作成(抽象クラス)
	abstract List<List<StringBuffer>> createTable(BufferedReader br) throws IOException ;
	
}
