/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.HoaDon;
import repository.HoaDonRepository;

/**
 *
 * @author admin
 */
public class HoaDonService {
    private HoaDonRepository hoaDonRepository = new HoaDonRepository();
    
    //chức năng xem
    public ArrayList<HoaDon> xemDanhSach(){
        return hoaDonRepository.layHoaDon();
    }
    
    //tính tổng DOANH THU
    public Double tongTienTheoThang (int thangCanTinh){
       return hoaDonRepository.TongDoanhThuThang(thangCanTinh);
    }
    
    public Integer themHoaDonMoi(HoaDon hoaDonCanThem){
        return hoaDonRepository.themDanhSach(hoaDonCanThem);
    }
}


