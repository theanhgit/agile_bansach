/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import helper.DbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

import model.HoaDon;
/**
 *
 * @author admin
 */
public class HoaDonRepository {
    private static ArrayList<HoaDon> dsHoaDon = new ArrayList<>();

//HIỂN THỊ LÊN TABLE
    public ArrayList<HoaDon> layHoaDon(){
        try{
        
            Connection cnn = DbConnector.getConnection(); //tạo đối tượng cnn kết nối đến csdl đã tạo
            String query= "select IDHoaDon,IDNhanVien,NgayBan,IDSach,TenSachBan,SoLuongBan,GiaBan, SoLuongBan*GiaBan as N'TongTien' from HOADON";
            System.out.println(query);
            Statement st = cnn.createStatement();// tạo đối tương st từ đối tương cnn = cách sử dụng phương thức createStatement() qua đó thực thi các câu lệnh SQL đến cơ sở dữ liệu
            ResultSet rs=st.executeQuery(query); //tạo đối tương rs từ đối tượng st sử dụng phương thức executeQuery() được gọi với truy vấn SQL 
                                            //và kết quả trả về được lưu trữ trong đối tượng ResultSet được gán cho biến rs
            dsHoaDon=new ArrayList<>();
            while(rs.next()){
                String maHoaDon=rs.getString(1);
                String maNhanVien=rs.getString(2);
                Date ngayBan=rs.getDate(3);
                String maSachBan=rs.getString(4);
                String tenSachBan=rs.getString(5);
                Integer soLuongBan=rs.getInt(6);
                Double giaBan=rs.getDouble(7);
                Double tongTien=rs.getDouble(8);
                dsHoaDon.add(new HoaDon(maHoaDon, maNhanVien, ngayBan, maSachBan, tenSachBan, soLuongBan, giaBan, tongTien));
            }          
        }
        catch(Exception e){
            e.printStackTrace();
        }

            return dsHoaDon;
    }
    
//TÍNH TỔNG DOANH THU THEO THÁNG
    public Double TongDoanhThuThang (int thang){
        Double tong = null ;
        try
        {
            Connection cnn=DbConnector.getConnection();
            String query="select SUM(SoLuongBan*GiaBan) as 'TongTien1Thang' from HOADON where month(NgayBan) =" + thang ;
            System.out.println(query);
            Statement st=cnn.createStatement();
            ResultSet rs=st.executeQuery(query);     
         while(rs.next()){
               tong = rs.getDouble("TongTien1Thang");
         }      
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return tong;
    }
    
    
    //Thêm
    public Integer themDanhSach(HoaDon hoaDonMoi){
        try{
            Connection cnn=DbConnector.getConnection();
            String query="INSERT INTO HOADON (IDHoaDon,IDNhanVien,NgayBan,IDSach,TenSachBan,SoLuongBan,GiaBan) VALUES ('"+hoaDonMoi.getMaHoaDon()+"','"+hoaDonMoi.getMaNhanVien()+"','"+hoaDonMoi.getNgayBan()+"','"+hoaDonMoi.getMaSachBan()+"',N'"+hoaDonMoi.getTenSachBan()+"',"+hoaDonMoi.getSoLuongBan()+","+hoaDonMoi.getGiaBan()+")";                    
            System.out.println(query);
            Statement st=cnn.createStatement();
            int result= st.executeUpdate(query);
            st.close();
            cnn.close();
            JOptionPane.showMessageDialog(null, "Thanh toán thành công, dữ liệu đã được lưu vào lịch sử giao dịch!");
        
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Mã sách hoặc Mã Nhân viên không tồn tại, Vui lòng nhập chính xác!");
            e.printStackTrace();
        }
        dsHoaDon.add(hoaDonMoi);
        return 1;
    }

    
}
