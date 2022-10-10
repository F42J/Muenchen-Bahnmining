package API;

import org.jdom.Element;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;

public class SQLConnector {

    private final String JDBC;
    private PreparedStatement sql;
    private Connection conn=null;
    public SQLConnector() {
        File file=new File(String.valueOf(Paths.get("identifier.sqlite")));
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JDBC="JDBC:sqlite:"+ Paths.get("identifier.sqlite");
        try {
            conn=DriverManager.getConnection(JDBC);
            conn.createStatement().execute("CREATE TABLE IF NOT EXISTS stops(station varchar," +
                    "id varchar," +
                    "apt varchar," +//arerival planned time
                    "app varchar," +//arrival planned platform
                    "l varchar," +//line
                    "appth varchar," +//arrival planned path
                    "dpt varchar," +//departure planned time
                    "dpp varchar," +//departure planned platform
                    "dppth varchar," +//departure planned path
                    "c varchar," +//category e.g. ICE, S
                    "f varchar," +//filter flags
                    "n varchar," +//train number
                    "o varchar," +//owner
                    "t varchar)");//trip type
            sql=conn.prepareStatement("INSERT INTO stops VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void insertPlannedStop(String stationname,Element stop) throws SQLException {
            Element ar=stop.getChild("ar");
            Element dp=stop.getChild("dp");
            Element tl=stop.getChild("tl");
            sql.setString(1,stationname);
            sql.setString(2,stop.getAttributeValue("id"));
            sql.setString(3,ar.getAttributeValue("pt"));
            sql.setString(4,ar.getAttributeValue("pp"));
            sql.setString(5,ar.getAttributeValue("l"));
            sql.setString(6,ar.getAttributeValue("ppth"));
            sql.setString(7,dp.getAttributeValue("pt"));
            sql.setString(8,dp.getAttributeValue("pp"));
            sql.setString(9,dp.getAttributeValue("ppth"));
            sql.setString(10,tl.getAttributeValue("c"));
            sql.setString(11,tl.getAttributeValue("f"));
            sql.setString(12,tl.getAttributeValue("n"));
            sql.setString(13,tl.getAttributeValue("o"));
            sql.setString(14,tl.getAttributeValue("t"));
            sql.execute();
        }
}
