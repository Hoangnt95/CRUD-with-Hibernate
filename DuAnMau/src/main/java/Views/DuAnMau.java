/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import DomainModels.ChiTietSP;
import DomainModels.ChucVu;
import DomainModels.CuaHang;
import DomainModels.DongSP;
import DomainModels.GioHang;
import DomainModels.GioHangChiTiet;
import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import DomainModels.KhachHang;
import DomainModels.MauSac;
import DomainModels.NhaSanXuat;
import DomainModels.NhanVien;
import DomainModels.SanPham;
import Repositories.impl.HoaDonCTRepository;
import Repositories.impl.NsxReporitory;
import Repositories.impl.SanPhamRepository;
import Services.impl.ChiTietSPService;
import Services.impl.ChucVuService;
import Services.impl.CuaHangService;
import Services.impl.DongSpService;
import Services.impl.GioHangService;
import Services.impl.HoaDonCTService;
import Services.impl.HoaDonService;
import Services.impl.KhachHangService;
import Services.impl.MauSacService;
import Services.impl.NhanVienService;
import Services.impl.NsxService;
import Services.impl.SanPhamService;
import Utilities.TinhTrangHD;
import Utilities.TrangThaiNV;

public class DuAnMau extends javax.swing.JFrame {

    private DefaultTableModel modelMauSac, modelDongSp, modelChucVu, modelNsx,
            modelKhachHang, modelCuaHang, modelSanPham, modelHoaDon, modelNhanVien,
            modelCTSP, modelGioHang, modelHoaDonCT;

    private List<GioHang> listsGioHang;
    private List<ChiTietSP> listsChiTietSP;
    private List<MauSac> listsMauSac;
    private List<NhanVien> listsNhanVien;
    private List<HoaDon> listsHoaDon;
    private List<DongSP> listsDongSp;
    private List<ChucVu> listsChucVu;
    private List<CuaHang> listsCuaHang;
    private List<KhachHang> listsKhachHang;
    private List<SanPham> listsSanPham;
    private List<NhaSanXuat> listsNsx;
    private List<HoaDonChiTiet> listHoaDonChiTiet;

    private ChiTietSPService chiTietSPService;
    private GioHangService gioHangService;
    private NsxService nsxService;
    private HoaDonService hdService;
    private DongSpService dongSpService;
    private MauSacService mauSacService;
    private ChucVuService chucVuService;
    private SanPhamService sanPhamService;
    private CuaHangService cuaHangService;
    private KhachHangService khachHangService;
    private NhanVienService nhanVienService;
    private HoaDonCTService hoaDonCTService;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    public DuAnMau() {
        initComponents();
        hoaDonCTService = new HoaDonCTService();
        chiTietSPService = new ChiTietSPService();
        gioHangService = new GioHangService();
        nsxService = new NsxService();
        mauSacService = new MauSacService();
        dongSpService = new DongSpService();
        chucVuService = new ChucVuService();
        sanPhamService = new SanPhamService();
        cuaHangService = new CuaHangService();
        khachHangService = new KhachHangService();
        hdService = new HoaDonService();
        nhanVienService = new NhanVienService();
        loadTableMauSac();
        loadTableChucVu();
        loadTableDongSP();
        loadTableHoaDon();
        loadTableCuaHang();
        loadTableSanPham();
        loadTableNhaSx();
        loadTableKhachHang();
        loadTableNhanVien();
        loadTableChiTietSP();
        loadTableHoaDonChiTiet();
    }

    private void clearData(JLabel lblId, JTextField txtMa, JTextField txtTen) {
        lblId.setText("-");
        txtMa.setText("");
        txtTen.setText("");
    }

    //-- Màu Sắc 
    private void loadTableMauSac() {
        listsMauSac = mauSacService.getLists();
        modelMauSac = (DefaultTableModel) tblMauSac.getModel();
        modelMauSac.setRowCount(0);
        cbbMauSac.removeAllItems();
        for (MauSac mauSac : listsMauSac) {
            cbbMauSac.addItem(mauSac.getTen());
            modelMauSac.addRow(new Object[]{mauSac.getMa(), mauSac.getTen()});
        }
    }

    private void fillDataMauSac(int index) {
        MauSac mauSac = listsMauSac.get(index);
        txtMaMau.setText(mauSac.getMa());
        txtTenMau.setText(mauSac.getTen());
        lblIdMauSac.setText(mauSac.getId());
    }

    private MauSac getMauSac() {
        MauSac mauSac = null;
        String ma = txtMaMau.getText();
        String ten = txtTenMau.getText();
        mauSac = new MauSac(ma, ten);
        return mauSac;
    }

    // -- Dòng Sản Phẩm
    private void loadTableDongSP() {
        listsDongSp = dongSpService.getLists();
        modelDongSp = (DefaultTableModel) tblDongSp.getModel();
        modelDongSp.setRowCount(0);
        cbbDongSP.removeAllItems();
        for (DongSP dongSP : listsDongSp) {
            cbbDongSP.addItem(dongSP.getTen());
            modelDongSp.addRow(new Object[]{dongSP.getMa(), dongSP.getTen()});
        }
    }

    private void fillDataDongSP(int index) {
        DongSP dongSP = listsDongSp.get(index);
        txtMaDongSp.setText(dongSP.getMa());
        txtTenDongSp.setText(dongSP.getTen());
        lblIdDongSp.setText(dongSP.getId());
    }

    private DongSP getDOngSP() {
        DongSP dongSP = null;
        String ma = txtMaDongSp.getText();
        String ten = txtTenDongSp.getText();
        dongSP = new DongSP(ma, ten);
        return dongSP;
    }

    // -- Chức Vụ
    private void loadTableChucVu() {
        listsChucVu = chucVuService.getLists();
        modelChucVu = (DefaultTableModel) tblChucVu.getModel();
        modelChucVu.setRowCount(0);
        cbbCV.removeAllItems();
        for (ChucVu chucVu : listsChucVu) {
            cbbCV.addItem(chucVu.getTen());
            modelChucVu.addRow(new Object[]{chucVu.getMa(), chucVu.getTen()});
        }
    }

    private void fillDataChucVu(int index) {
        ChucVu chucVu = listsChucVu.get(index);
        txtMaChucVu.setText(chucVu.getMa());
        txtTenChucVu.setText(chucVu.getTen());
        lblIdChucVu.setText(chucVu.getId());
    }

    private ChucVu getChucVu() {
        ChucVu chucVu = null;
        String ma = txtMaChucVu.getText();
        String ten = txtTenChucVu.getText();
        chucVu = new ChucVu(ma, ten);
        return chucVu;
    }

    // -- Sản phẩm
    private void loadTableSanPham() {
        listsSanPham = sanPhamService.getLists();
        modelSanPham = (DefaultTableModel) tblSp.getModel();
        modelSanPham.setRowCount(0);
        cbbSP.removeAllItems();
        for (SanPham sanPham : listsSanPham) {
            cbbSP.addItem(sanPham.getTen());
            modelSanPham.addRow(new Object[]{sanPham.getMa(), sanPham.getTen()});
        }
    }

    private void fillDataSanPham(int index) {
        SanPham sanPham = listsSanPham.get(index);
        txtMaSp.setText(sanPham.getMa());
        txtTenSp.setText(sanPham.getTen());
        lblIdSp.setText(sanPham.getId());
    }

    private SanPham getSanPham() {
        SanPham sanPham = null;
        String ma = txtMaSp.getText();
        String ten = txtTenSp.getText();
        sanPham = new SanPham(ma, ten);
        return sanPham;
    }

    // --Nha san xuat
    private void loadTableNhaSx() {
        listsNsx = nsxService.getLists();
        modelNsx = (DefaultTableModel) tblNsx.getModel();
        modelNsx.setRowCount(0);
        cbbNSX.removeAllItems();
        for (NhaSanXuat nsx : listsNsx) {
            cbbNSX.addItem(nsx.getTen());
            modelNsx.addRow(new Object[]{nsx.getMa(), nsx.getTen()});
        }
    }

    private void fillDataNhaSx(int index) {
        NhaSanXuat nsx = listsNsx.get(index);
        txtMaNsx.setText(nsx.getMa());
        txtTenNsx.setText(nsx.getTen());
        lblIdNsx.setText(nsx.getId());
    }

    private NhaSanXuat getNhaSanXuat() {
        NhaSanXuat nsx = null;
        String ma = txtMaNsx.getText();
        String ten = txtTenNsx.getText();
        nsx = new NhaSanXuat(ma, ten);
        return nsx;
    }

    // -- Cửa hàng
    private void loadTableCuaHang() {
        listsCuaHang = cuaHangService.getLists();
        modelCuaHang = (DefaultTableModel) tblCuaHang.getModel();
        modelCuaHang.setRowCount(0);
        cbbCH.removeAllItems();
        for (CuaHang cuaHang : listsCuaHang) {
            cbbCH.addItem(cuaHang.getTen());
            modelCuaHang.addRow(new Object[]{cuaHang.getMa(), cuaHang.getTen(),
                cuaHang.getDiaChi(), cuaHang.getThanhPho(), cuaHang.getQuocGia()});
        }
    }

    private void fillDataCuaHang(int index) {
        CuaHang cuaHang = listsCuaHang.get(index);
        txtDiaChiCuaHang.setText(cuaHang.getDiaChi());
        txtMaCuaHang.setText(cuaHang.getMa());
        txtTenCuaHang.setText(cuaHang.getTen());
        lblIdCuaHang.setText(cuaHang.getId());
        cbbTpCuaHang.setSelectedItem((String) cuaHang.getThanhPho());
        cbbQuocGiaCuaHang.setSelectedItem((String) cuaHang.getQuocGia());
    }

    private CuaHang getCuaHang() {
        CuaHang cuaHang = null;
        String ma = txtMaCuaHang.getText();
        String ten = txtTenCuaHang.getText();
        String diaChi = txtDiaChiCuaHang.getText();
        String thanhPho = (String) cbbTpCuaHang.getSelectedItem();
        String quocGia = (String) cbbTpCuaHang.getSelectedItem();
        cuaHang = new CuaHang(ma, ten, diaChi, thanhPho, quocGia);
        return cuaHang;
    }

    // -- Khách hàng
    private void loadTableKhachHang() {
        listsKhachHang = khachHangService.getLists();
        modelKhachHang = (DefaultTableModel) tblKhachHang.getModel();
        modelKhachHang.setRowCount(0);
        cbbKH.removeAllItems();
        for (KhachHang khachHang : listsKhachHang) {
            cbbKH.addItem(khachHang.getTen());
            modelKhachHang.addRow(new Object[]{khachHang.getMa(), khachHang.getTen(),
                khachHang.getTenDem(), khachHang.getHo(), khachHang.getNgaySinh(),
                khachHang.getSdt(), khachHang.getDiaChi(), khachHang.getThanhPho(),
                khachHang.getQuocGia(), khachHang.getMatKhau()});
        }
    }

    private void fillDataKhachHang(int index) {
        KhachHang khachHang = listsKhachHang.get(index);
        lblIdkhachHang.setText(khachHang.getId());
        txtTenKhachHang.setText(khachHang.getTen());
        txtTenDemKH.setText(khachHang.getTenDem());
        txtHoKH.setText(khachHang.getHo());
        txtNgSinh.setText(dateFormat.format(khachHang.getNgaySinh()));
        txtSdt.setText(khachHang.getSdt());
        txtDiaChiKH.setText(khachHang.getDiaChi());
        cbbTpKH.setSelectedItem((String) khachHang.getThanhPho());
        cbbQuocGiaKH.setSelectedItem((String) khachHang.getQuocGia());
        txtMatKhau.setText(khachHang.getMatKhau());
    }

    private KhachHang getKhachHang() throws ParseException {
        KhachHang khachHang = null;
        String ma = txtMaKhachHang.getText();
        String ten = txtTenKhachHang.getText();
        String tenDem = txtTenDemKH.getText();
        String ho = txtHoKH.getText();
        String diaChi = txtDiaChiKH.getText();
        String matKhau = txtMatKhau.getText();
        Date ngaySinh = dateFormat.parse(txtNgSinh.getText());
        String sdt = txtSdt.getText();
        String thanhPho = (String) cbbTpKH.getSelectedItem();
        String quocGia = (String) cbbQuocGiaKH.getSelectedItem();
        khachHang = new KhachHang(ma, ten, tenDem, ho, ngaySinh, sdt, diaChi, thanhPho, quocGia, matKhau);
        return khachHang;
    }

    // -- Hóa đơn
    private void loadTableHoaDon() {
        listsHoaDon = hdService.getLists();
        modelHoaDon = (DefaultTableModel) tblHD.getModel();
        cbbTinhTrang.removeAllItems();
        cbbMaHDCT.removeAllItems();
        loadDataCBBHD();
        modelHoaDon.setRowCount(0);
        for (HoaDon hoaDon : listsHoaDon) {
            cbbMaHDCT.addItem(hoaDon.getMa());
            modelHoaDon.addRow(new Object[]{hoaDon.getMa(), hoaDon.getKhachHang().getTen(),
                hoaDon.getNhanVien().getTen(), hoaDon.getNgayTao(), hoaDon.getNgayThanhToan(),
                hoaDon.getNgayShip(), hoaDon.getNgayNhan(), hoaDon.getTenNguoiNhan(),
                hoaDon.getDiaChi(), hoaDon.getSdt()});
        }
    }

    private void loadDataCBBHD() {
        for (TinhTrangHD tinhTrangHD : TinhTrangHD.values()) {
            cbbTinhTrang.addItem((tinhTrangHD.toString()));
        }
    }

    private void fillDataHoaDon(int index) {
        HoaDon hoaDon = listsHoaDon.get(index);
        lblIdHD.setText(hoaDon.getId());
        txtMaHD.setText(hoaDon.getMa());
        cbbKH.setSelectedItem((String) hoaDon.getKhachHang().getMa());
        cbbNV.setSelectedItem((String) hoaDon.getNhanVien().getMa());
        txtNgayTaoHD.setText(dateFormat.format(hoaDon.getNgayTao()));
        txtNgayThanhToanHD.setText(dateFormat.format(hoaDon.getNgayThanhToan()));
        txtNgayShip.setText(dateFormat.format(hoaDon.getNgayShip()));
        txtNgayNhanHD.setText(dateFormat.format(hoaDon.getNgayNhan()));
        txtTenNguoiNhan.setText(hoaDon.getTenNguoiNhan());
        txtDiaChiNguoiNhan.setText(hoaDon.getDiaChi());
        txtSdtNgNhan.setText(hoaDon.getSdt());
        cbbTinhTrang.setSelectedItem(hoaDon.getTinhTrang());
    }

    private HoaDon getHoaDon() throws ParseException {
        HoaDon hoaDon = null;
        String maKHStr = (String) cbbKH.getSelectedItem();
        String maNVStr = (String) cbbNV.getSelectedItem();
        String maHD = txtMaHD.getText();
        Date ngayTao = dateFormat.parse(txtNgayTaoHD.getText());
        Date ngayThanhToan = dateFormat.parse(txtNgayThanhToanHD.getText());
        Date ngayShip = dateFormat.parse(txtNgayShip.getText());
        Date ngayNhan = dateFormat.parse(txtNgayNhanHD.getText());
        TinhTrangHD tinhTrangHD = TinhTrangHD.valueOf((String) cbbTinhTrang.getSelectedItem());
        String tenNguoiNhan = txtTenNguoiNhan.getText();
        String diaChi = txtTenNguoiNhan.getText();
        String sdt = txtSdtNgNhan.getText();

        KhachHang khachHang = this.khachHangService.getOne(maKHStr);
        NhanVien nhanVien = this.nhanVienService.getOne(maNVStr);
        hoaDon = new HoaDon(khachHang, nhanVien, maHD, ngayTao, ngayThanhToan, ngayShip,
                ngayNhan, tinhTrangHD, tenNguoiNhan, diaChi, sdt);
        return hoaDon;
    }

    // -- Nhân viên
    private void loadTableNhanVien() {
        listsNhanVien = nhanVienService.getLists();
        modelNhanVien = (DefaultTableModel) tblNV.getModel();
        modelNhanVien.setRowCount(0);
        loadDataCBBNV();
        cbbNV.removeAllItems();
        for (NhanVien nhanVien : listsNhanVien) {
            cbbNV.addItem(nhanVien.getMa());
            modelNhanVien.addRow(new Object[]{nhanVien.getMa(), nhanVien.getTen(),
                nhanVien.getTenDem(), nhanVien.getHo(), nhanVien.getDiaChi(),
                nhanVien.getTrangThai(), nhanVien.getSdt(), nhanVien.getGioiTinh(),
                nhanVien.getNgaySinh(), nhanVien.getMatKhau(), nhanVien.getCuaHang().getTen(),
                nhanVien.getChucVu().getTen()});
        }
    }

    private void loadDataCBBNV() {
        for (TrangThaiNV trangThaiNV : TrangThaiNV.values()) {
            cbbTrangThai.addItem(trangThaiNV.toString());
        }
    }

    private void fillDataNhanVien(int index) {
        NhanVien nhanVien = listsNhanVien.get(index);
        lblIdNV.setText(nhanVien.getId());
        txtMaNV.setText(nhanVien.getMa());
        txtTenNV.setText(nhanVien.getTen());
        txtTenDemNV.setText(nhanVien.getTenDem());
        txtHoNV.setText(nhanVien.getHo());
        txtDiaChiNV.setText(nhanVien.getDiaChi());
        cbbTrangThai.setSelectedItem(nhanVien.getTrangThai());
        txtSdtNV.setText(nhanVien.getSdt());
        if (nhanVien.getGioiTinh().equalsIgnoreCase("nam")) {
            radioNam.setSelected(true);
        } else {
            radioNu.setSelected(true);
        }
        txtNgSinhNV.setText(dateFormat.format(nhanVien.getNgaySinh()));
        txtMatKhauNV.setText(nhanVien.getMatKhau());
        cbbCH.setSelectedItem((String) nhanVien.getCuaHang().getTen());
        cbbCV.setSelectedItem((String) nhanVien.getChucVu().getTen());
    }

    private NhanVien getNhanVien() throws ParseException {
        NhanVien nhanVien = null;
        String ma = txtMaNV.getText();
        String ten = txtTenNV.getText();
        String tenDem = txtTenDemNV.getText();
        String ho = txtHoNV.getText();
        String gioiTinh = "";
        if (radioNam.isSelected()) {
            gioiTinh = "Nam";
        } else {
            gioiTinh = "Nu";
        }
        Date ngSinh = dateFormat.parse(txtNgSinhNV.getText());
        String diaChi = txtDiaChiNV.getText();
        String sdt = txtSdtNV.getText();
        String matKhau = txtMatKhauNV.getText();
        TrangThaiNV trangThaiNV = TrangThaiNV.valueOf((String) cbbTrangThai.getSelectedItem());
        String idBuiBC = lblIdGuiBC.getText();
        String cuaHangStr = (String) cbbCH.getSelectedItem();
        String chucVuStr = (String) cbbCV.getSelectedItem();

        CuaHang cuaHang = this.cuaHangService.getOne(cuaHangStr);
        ChucVu chucVu = this.chucVuService.getOne(chucVuStr);

        nhanVien = new NhanVien(ma, ten, tenDem, ho, gioiTinh, ngSinh, diaChi,
                sdt, matKhau, cuaHang, chucVu, null, trangThaiNV);
        return nhanVien;
    }

    // -- Chi tiet SP
    private void loadTableChiTietSP() {
        listsChiTietSP = chiTietSPService.getLists();
        modelCTSP = (DefaultTableModel) tblCTSP.getModel();
        modelCTSP.setRowCount(0);
        cbbMaSPHDCT.removeAllItems();
        for (ChiTietSP chiTietSP : listsChiTietSP) {
            cbbMaSPHDCT.addItem(chiTietSP.getSanPham().getTen());
            modelCTSP.addRow(new Object[]{chiTietSP.getSanPham().getTen(),
                chiTietSP.getNhaSanXuat().getTen(), chiTietSP.getMauSac().getTen(),
                chiTietSP.getDongSP().getTen(), chiTietSP.getNamBH(), chiTietSP.getGiaNhap(),
                chiTietSP.getGiaBan(), chiTietSP.getMoTa()});
        }
    }

    private void fillDataCTSP(int index) {
        ChiTietSP chiTietSP = listsChiTietSP.get(index);
        lblIdCTSP.setText(chiTietSP.getId());
        cbbSP.setSelectedItem((String) chiTietSP.getSanPham().getTen());
        cbbNSX.setSelectedItem((String) chiTietSP.getNhaSanXuat().getTen());
        cbbMauSac.setSelectedItem((String) chiTietSP.getMauSac().getTen());
        cbbDongSP.setSelectedItem((String) chiTietSP.getDongSP().getTen());
        txtNamBHCTSP.setText((String.valueOf(chiTietSP.getNamBH())));
        txtSoLuongTon.setText(String.valueOf(chiTietSP.getSoLuongTon()));
        txtGiaNhapCTSP.setText(String.valueOf(chiTietSP.getGiaNhap()));
        txtGiaBanCTSP.setText(String.valueOf(chiTietSP.getGiaBan()));
        txtMoTaCTSP.setText(chiTietSP.getMoTa());
    }

    private ChiTietSP getChiTietSP() {
        ChiTietSP chiTietSP = null;
        String sanPhamStr = (String) cbbSP.getSelectedItem();
        String nhaSanXuatStr = (String) cbbNSX.getSelectedItem();
        String mauSacStr = (String) cbbMauSac.getSelectedItem();
        String dongSPStr = (String) cbbDongSP.getSelectedItem();
        int namBH = Integer.parseInt(txtNamBHCTSP.getText());
        String moTa = txtMoTaCTSP.getText();
        int soLuongTon = Integer.parseInt(txtSoLuongTon.getText());
        BigDecimal giaNhap = new BigDecimal(txtGiaNhapCTSP.getText());
        BigDecimal giaBan = new BigDecimal(txtGiaBanCTSP.getText());

        SanPham sanPham = this.sanPhamService.getOne(sanPhamStr);
        NhaSanXuat nhaSanXuat = this.nsxService.getOne(nhaSanXuatStr);
        MauSac mauSac = this.mauSacService.getOne(mauSacStr);
        DongSP dongSP = this.dongSpService.getOne(dongSPStr);

        chiTietSP = new ChiTietSP(sanPham, nhaSanXuat, mauSac, dongSP, namBH,
                moTa, soLuongTon, giaNhap, giaBan);

        return chiTietSP;
    }

    private void loadTableHoaDonChiTiet() {
        listHoaDonChiTiet = hoaDonCTService.getLists();
        modelHoaDonCT = (DefaultTableModel) tblHoaDOnCT.getModel();
        modelHoaDonCT.setRowCount(0);
        for (HoaDonChiTiet hoaDonChiTiet : listHoaDonChiTiet) {
            modelHoaDonCT.addRow(new Object[]{hoaDonChiTiet.getIdHoaDon().getMa(),
                hoaDonChiTiet.getIdChiTietSP().getSanPham().getTen(), hoaDonChiTiet.getSoLuong(),
                hoaDonChiTiet.getDonGia()});
        }
    }

    private HoaDonChiTiet getHoaDonCT() {
        HoaDonChiTiet hoaDonChiTiet = null;
        String idHoaDon = (String) cbbMaHDCT.getSelectedItem();
        String idChiTietSP = (String) cbbMaSPHDCT.getSelectedItem();
        int soLuong = Integer.parseInt(txtSoLuongHDCT.getText());
        BigDecimal donGia = new BigDecimal(txtDonGiaHDCT.getText());

        HoaDon hoaDon = this.hdService.getOne(idHoaDon);
        ChiTietSP chiTietSP = this.chiTietSPService.getOne(idChiTietSP);

        hoaDonChiTiet = new HoaDonChiTiet(hoaDon, chiTietSP, soLuong, donGia);
        return hoaDonChiTiet;
    }

    private void fillDataHoaDonCT(int index) {
        HoaDonChiTiet hoaDonChiTiet = listHoaDonChiTiet.get(index);
        cbbMaHDCT.setSelectedItem(hoaDonChiTiet.getIdHoaDon().getMa());
        cbbMaSPHDCT.setSelectedItem(hoaDonChiTiet.getIdChiTietSP().getSanPham().getTen());
        txtSoLuongHDCT.setText(String.valueOf(hoaDonChiTiet.getSoLuong()));
        txtDonGiaHDCT.setText(String.valueOf(hoaDonChiTiet.getDonGia()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblIdDongSp = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaDongSp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTenDongSp = new javax.swing.JTextField();
        btnThemDongSp = new javax.swing.JButton();
        btnSuaDongSp = new javax.swing.JButton();
        btnXoaDongSp = new javax.swing.JButton();
        btnClearDongSp = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDongSp = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblIdMauSac = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaMau = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTenMau = new javax.swing.JTextField();
        btnThemMauSac = new javax.swing.JButton();
        btnSuaMauSac = new javax.swing.JButton();
        btnXoaMauSac = new javax.swing.JButton();
        btnClearMauSac = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMauSac = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblIdChucVu = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMaChucVu = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTenChucVu = new javax.swing.JTextField();
        btnThemChucVu = new javax.swing.JButton();
        btnSuaChucVu = new javax.swing.JButton();
        btnXoaChucVu = new javax.swing.JButton();
        btnClearChucVu = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChucVu = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lblIdNsx = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtMaNsx = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTenNsx = new javax.swing.JTextField();
        btnThemNsx = new javax.swing.JButton();
        btnSuaNsx = new javax.swing.JButton();
        btnXoaNsx = new javax.swing.JButton();
        btnClearNsx = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblNsx = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        lblIdSp = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtMaSp = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtTenSp = new javax.swing.JTextField();
        btnThemSp = new javax.swing.JButton();
        btnSuaSp = new javax.swing.JButton();
        btnXoaSp = new javax.swing.JButton();
        btnClearSp = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSp = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        lblIdCuaHang = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtMaCuaHang = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTenCuaHang = new javax.swing.JTextField();
        btnThemCuaHang = new javax.swing.JButton();
        btnSuaCuaHang = new javax.swing.JButton();
        btnXoaCuaHang = new javax.swing.JButton();
        btnClearCuaHang = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblCuaHang = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        cbbQuocGiaCuaHang = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cbbTpCuaHang = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        txtDiaChiCuaHang = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        lblIdkhachHang = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        btnThemKH = new javax.swing.JButton();
        btnSuaKH = new javax.swing.JButton();
        btnXoaKH = new javax.swing.JButton();
        btnClearKH = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        cbbQuocGiaKH = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        cbbTpKH = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        txtDiaChiKH = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtTenDemKH = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtHoKH = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtNgSinh = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        lblIdHD = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        cbbKH = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        cbbNV = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtNgayTaoHD = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtNgayThanhToanHD = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtNgayShip = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtNgayNhanHD = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtTenNguoiNhan = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        cbbTinhTrang = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        txtDiaChiNguoiNhan = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txtSdtNgNhan = new javax.swing.JTextField();
        txtMaHD = new javax.swing.JTextField();
        btnThemHD = new javax.swing.JButton();
        btnSuaHD = new javax.swing.JButton();
        btnXoaHD = new javax.swing.JButton();
        btnLamMoiHD = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblHD = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        lblIdNV = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txtNgSinhNV = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtHoNV = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txtMatKhauNV = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        cbbTrangThai = new javax.swing.JComboBox<>();
        jLabel56 = new javax.swing.JLabel();
        txtDiaChiNV = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        txtSdtNV = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        btnThemNV = new javax.swing.JButton();
        btnSuaNV = new javax.swing.JButton();
        btnXoaNV = new javax.swing.JButton();
        btnLamMoiNV = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblNV = new javax.swing.JTable();
        txtTenNV = new javax.swing.JTextField();
        txtTenDemNV = new javax.swing.JTextField();
        radioNam = new javax.swing.JRadioButton();
        radioNu = new javax.swing.JRadioButton();
        lblIdGuiBC = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        cbbCH = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        cbbCV = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        lblIdCTSP = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        cbbSP = new javax.swing.JComboBox<>();
        jLabel62 = new javax.swing.JLabel();
        cbbNSX = new javax.swing.JComboBox<>();
        jLabel66 = new javax.swing.JLabel();
        txtNamBHCTSP = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        txtSoLuongTon = new javax.swing.JTextField();
        btnThemCTSP = new javax.swing.JButton();
        btnSuaCTSP = new javax.swing.JButton();
        btnXoaCTSP = new javax.swing.JButton();
        btnLamMoiCTSP = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblCTSP = new javax.swing.JTable();
        jLabel63 = new javax.swing.JLabel();
        cbbMauSac = new javax.swing.JComboBox<>();
        jLabel64 = new javax.swing.JLabel();
        cbbDongSP = new javax.swing.JComboBox<>();
        jLabel67 = new javax.swing.JLabel();
        txtGiaNhapCTSP = new javax.swing.JTextField();
        txtGiaBanCTSP = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        txtMoTaCTSP = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        txtSoLuongHDCT = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        txtDonGiaHDCT = new javax.swing.JTextField();
        btnThemHDCT = new javax.swing.JButton();
        btnSuaHDCT = new javax.swing.JButton();
        btnXoaHDCT = new javax.swing.JButton();
        btnClearHDCT = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblHoaDOnCT = new javax.swing.JTable();
        cbbMaHDCT = new javax.swing.JComboBox<>();
        lblMaSP = new javax.swing.JLabel();
        cbbMaSPHDCT = new javax.swing.JComboBox<>();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Id");

        lblIdDongSp.setText("-");

        jLabel7.setText("Ma dongSP");

        jLabel8.setText("Ten DongSP");

        btnThemDongSp.setText("Them");
        btnThemDongSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDongSpActionPerformed(evt);
            }
        });

        btnSuaDongSp.setText("Sua");
        btnSuaDongSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDongSpActionPerformed(evt);
            }
        });

        btnXoaDongSp.setText("Xoa");
        btnXoaDongSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDongSpActionPerformed(evt);
            }
        });

        btnClearDongSp.setText("Lam moi");
        btnClearDongSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearDongSpActionPerformed(evt);
            }
        });

        tblDongSp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã Dòng SP", "Tên Dòng SP"
            }
        ));
        tblDongSp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDongSpMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDongSp);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblIdDongSp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaDongSp)
                            .addComponent(txtTenDongSp, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnThemDongSp)
                        .addGap(49, 49, 49)
                        .addComponent(btnSuaDongSp)
                        .addGap(53, 53, 53)
                        .addComponent(btnXoaDongSp)
                        .addGap(69, 69, 69)
                        .addComponent(btnClearDongSp)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblIdDongSp))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMaDongSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTenDongSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemDongSp)
                    .addComponent(btnSuaDongSp)
                    .addComponent(btnXoaDongSp)
                    .addComponent(btnClearDongSp))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 845, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 239, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Dòng Sản Phẩm", jPanel2);

        jLabel2.setText("Id");

        lblIdMauSac.setText("-");

        jLabel4.setText("Ma mau");

        jLabel5.setText("Ten mau");

        btnThemMauSac.setText("Them");
        btnThemMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMauSacActionPerformed(evt);
            }
        });

        btnSuaMauSac.setText("Sua");
        btnSuaMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaMauSacActionPerformed(evt);
            }
        });

        btnXoaMauSac.setText("Xoa");
        btnXoaMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaMauSacActionPerformed(evt);
            }
        });

        btnClearMauSac.setText("Lam moi");
        btnClearMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearMauSacActionPerformed(evt);
            }
        });

        tblMauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã màu", "Tên màu"
            }
        ));
        tblMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauSacMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMauSac);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblIdMauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMaMau)
                                    .addComponent(txtTenMau, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnThemMauSac)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSuaMauSac)
                                .addGap(41, 41, 41)
                                .addComponent(btnXoaMauSac)
                                .addGap(37, 37, 37)))
                        .addComponent(btnClearMauSac)))
                .addContainerGap(376, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblIdMauSac))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMaMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemMauSac)
                    .addComponent(btnSuaMauSac)
                    .addComponent(btnXoaMauSac)
                    .addComponent(btnClearMauSac))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Màu Sắc", jPanel1);

        jLabel6.setText("Id");

        lblIdChucVu.setText("-");

        jLabel9.setText("Ma");

        jLabel10.setText("Ten");

        btnThemChucVu.setText("Them");
        btnThemChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChucVuActionPerformed(evt);
            }
        });

        btnSuaChucVu.setText("Sua");
        btnSuaChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaChucVuActionPerformed(evt);
            }
        });

        btnXoaChucVu.setText("Xoa");
        btnXoaChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaChucVuActionPerformed(evt);
            }
        });

        btnClearChucVu.setText("Lam moi");
        btnClearChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearChucVuActionPerformed(evt);
            }
        });

        tblChucVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã chức vụ", "Tên chức vụ"
            }
        ));
        tblChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChucVuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblChucVu);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblIdChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMaChucVu)
                                    .addComponent(txtTenChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnThemChucVu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSuaChucVu)
                                .addGap(41, 41, 41)
                                .addComponent(btnXoaChucVu)
                                .addGap(37, 37, 37)))
                        .addComponent(btnClearChucVu)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblIdChucVu))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtMaChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTenChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemChucVu)
                    .addComponent(btnSuaChucVu)
                    .addComponent(btnXoaChucVu)
                    .addComponent(btnClearChucVu))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 845, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Chức Vụ", jPanel4);

        jLabel11.setText("Id");

        lblIdNsx.setText("-");

        jLabel12.setText("Ma");

        jLabel13.setText("Ten");

        btnThemNsx.setText("Them");
        btnThemNsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNsxActionPerformed(evt);
            }
        });

        btnSuaNsx.setText("Sua");
        btnSuaNsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNsxActionPerformed(evt);
            }
        });

        btnXoaNsx.setText("Xoa");
        btnXoaNsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNsxActionPerformed(evt);
            }
        });

        btnClearNsx.setText("Lam moi");
        btnClearNsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearNsxActionPerformed(evt);
            }
        });

        tblNsx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã Nsx", "Tên Nsx"
            }
        ));
        tblNsx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNsxMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblNsx);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblIdNsx, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMaNsx)
                                    .addComponent(txtTenNsx, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(btnThemNsx)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSuaNsx)
                                .addGap(41, 41, 41)
                                .addComponent(btnXoaNsx)
                                .addGap(37, 37, 37)))
                        .addComponent(btnClearNsx)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblIdNsx))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtMaNsx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTenNsx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNsx)
                    .addComponent(btnSuaNsx)
                    .addComponent(btnXoaNsx)
                    .addComponent(btnClearNsx))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 845, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Nhà Sản Xuất", jPanel6);

        jLabel14.setText("Id");

        lblIdSp.setText("-");

        jLabel15.setText("Ma");

        jLabel16.setText("Ten");

        btnThemSp.setText("Them");
        btnThemSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSpActionPerformed(evt);
            }
        });

        btnSuaSp.setText("Sua");
        btnSuaSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSpActionPerformed(evt);
            }
        });

        btnXoaSp.setText("Xoa");
        btnXoaSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSpActionPerformed(evt);
            }
        });

        btnClearSp.setText("Lam moi");
        btnClearSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSpActionPerformed(evt);
            }
        });

        tblSp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã Sp", "Tên Sp"
            }
        ));
        tblSp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSpMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblSp);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblIdSp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMaSp)
                                    .addComponent(txtTenSp, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btnThemSp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSuaSp)
                                .addGap(41, 41, 41)
                                .addComponent(btnXoaSp)
                                .addGap(37, 37, 37)))
                        .addComponent(btnClearSp)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblIdSp))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTenSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSp)
                    .addComponent(btnSuaSp)
                    .addComponent(btnXoaSp)
                    .addComponent(btnClearSp))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 845, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel8);

        jLabel17.setText("Id");

        lblIdCuaHang.setText("-");

        jLabel18.setText("Ma");

        jLabel19.setText("Ten");

        btnThemCuaHang.setText("Them");
        btnThemCuaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCuaHangActionPerformed(evt);
            }
        });

        btnSuaCuaHang.setText("Sua");
        btnSuaCuaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCuaHangActionPerformed(evt);
            }
        });

        btnXoaCuaHang.setText("Xoa");
        btnXoaCuaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCuaHangActionPerformed(evt);
            }
        });

        btnClearCuaHang.setText("Lam moi");
        btnClearCuaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearCuaHangActionPerformed(evt);
            }
        });

        tblCuaHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã cửa hàng", "Tên cửa hàng", "Địa chỉ", "Thành phố", "Quốc gia"
            }
        ));
        tblCuaHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCuaHangMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblCuaHang);

        jLabel20.setText("Quoc Gia");

        cbbQuocGiaCuaHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Việt Nam", "Mỹ", "Anh", "Trung Quốc", "Hàn Quốc" }));

        jLabel21.setText("Thanh Pho");

        cbbTpCuaHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hà Nội", "Ninh Bình", "Seul", "Thâm Quyến" }));

        jLabel22.setText("Dia chi");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addComponent(btnThemCuaHang)
                                .addGap(98, 98, 98)
                                .addComponent(btnSuaCuaHang))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblIdCuaHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMaCuaHang)
                                    .addComponent(txtTenCuaHang, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel21))
                                    .addComponent(jLabel22))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDiaChiCuaHang)
                                    .addComponent(cbbTpCuaHang, 0, 254, Short.MAX_VALUE)
                                    .addComponent(cbbQuocGiaCuaHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnXoaCuaHang)
                                .addGap(113, 113, 113)
                                .addComponent(btnClearCuaHang)))))
                .addGap(170, 170, 170))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblIdCuaHang))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtMaCuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtTenCuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(cbbQuocGiaCuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(cbbTpCuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtDiaChiCuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnXoaCuaHang)
                                    .addComponent(btnClearCuaHang))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnSuaCuaHang)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThemCuaHang)))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 63, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cửa Hàng", jPanel10);

        jLabel23.setText("Id");

        lblIdkhachHang.setText("-");

        jLabel24.setText("Ma");

        jLabel25.setText("Ten");

        btnThemKH.setText("Them");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        btnSuaKH.setText("Sua");
        btnSuaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKHActionPerformed(evt);
            }
        });

        btnXoaKH.setText("Xoa");
        btnXoaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKHActionPerformed(evt);
            }
        });

        btnClearKH.setText("Lam moi");
        btnClearKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearKHActionPerformed(evt);
            }
        });

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Tên KH", "Tên đệm", "Họ", "Ngày sinh", "Sdt", "Địa chỉ", "Thành phố", "Quốc gia", "Mật khẩu"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblKhachHang);

        jLabel26.setText("Quoc Gia");

        cbbQuocGiaKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Việt Nam", "Mỹ", "Anh", "Trung Quốc", "Hàn Quốc" }));

        jLabel27.setText("Thanh Pho");

        cbbTpKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hà Nội", "Ninh Bình", "Seul", "Thâm Quyến" }));

        jLabel28.setText("Dia chi");

        jLabel29.setText("Ten dem");

        jLabel30.setText("Ho");

        jLabel31.setText("Sdt");

        jLabel32.setText("Ngay sinh");

        jLabel33.setText("Mat khau");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnThemKH, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblIdkhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMaKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                            .addComponent(txtTenKhachHang)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel29))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenDemKH)
                                    .addComponent(txtNgSinh)
                                    .addComponent(txtHoKH))))
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel26)
                                            .addComponent(jLabel27))
                                        .addGap(40, 40, 40)
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbbTpKH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbbQuocGiaKH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel28)
                                            .addComponent(jLabel31)
                                            .addComponent(jLabel33))
                                        .addGap(47, 47, 47)
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMatKhau)
                                            .addComponent(txtDiaChiKH)
                                            .addComponent(txtSdt))))
                                .addGap(26, 26, 26))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(btnSuaKH)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnXoaKH)
                                .addGap(140, 140, 140)))
                        .addComponent(btnClearKH))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblIdkhachHang))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(cbbQuocGiaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(cbbTpKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtDiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(txtTenDemKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtHoKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(txtNgSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemKH)
                    .addComponent(btnXoaKH)
                    .addComponent(btnClearKH)
                    .addComponent(btnSuaKH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 63, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Khách Hàng", jPanel12);

        jLabel34.setText("Id");

        lblIdHD.setText("-");

        jLabel36.setText("Khach hang");

        jLabel37.setText("Nhan vien");

        jLabel38.setText("Ma");

        jLabel40.setText("Ngay tao");

        jLabel41.setText("Ngay thanh toan");

        jLabel42.setText("Ngay ship");

        jLabel43.setText("Ngay nhan");

        jLabel45.setText("Ten nguoi nhan");

        jLabel46.setText("Tinh trang");

        jLabel47.setText("Dia chi");

        jLabel49.setText("Sdt");

        btnThemHD.setText("Them");
        btnThemHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHDActionPerformed(evt);
            }
        });

        btnSuaHD.setText("Sua");
        btnSuaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaHDActionPerformed(evt);
            }
        });

        btnXoaHD.setText("Xoa");
        btnXoaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHDActionPerformed(evt);
            }
        });

        btnLamMoiHD.setText("Lam moi");
        btnLamMoiHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiHDActionPerformed(evt);
            }
        });

        tblHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Ma HD", "Khach hang", "Nhan vien", "Ngay tao", "Ngay thanh toan", "Ngay ship", "Ngay nhan", "Ten nguoi nhan", "Dia chi", "Sdt"
            }
        ));
        tblHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblHD);
        if (tblHD.getColumnModel().getColumnCount() > 0) {
            tblHD.getColumnModel().getColumn(5).setHeaderValue("Ngay ship");
            tblHD.getColumnModel().getColumn(6).setHeaderValue("Ngay nhan");
        }

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37)
                            .addComponent(jLabel42)
                            .addComponent(jLabel46)
                            .addComponent(jLabel47))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(txtDiaChiNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel45))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgayShip, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblIdHD, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbbNV, javax.swing.GroupLayout.Alignment.LEADING, 0, 120, Short.MAX_VALUE)
                                    .addComponent(cbbKH, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgayTaoHD)
                            .addComponent(txtNgayThanhToanHD)
                            .addComponent(txtNgayNhanHD)
                            .addComponent(txtTenNguoiNhan)
                            .addComponent(txtSdtNgNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(txtMaHD))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnThemHD)
                        .addGap(132, 132, 132)
                        .addComponent(btnSuaHD)
                        .addGap(160, 160, 160)
                        .addComponent(btnXoaHD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLamMoiHD)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 798, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(35, Short.MAX_VALUE))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(lblIdHD)
                    .addComponent(jLabel38)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(cbbKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(txtNgayTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(cbbNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(txtNgayThanhToanHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel42)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNgayShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel43)
                        .addComponent(txtNgayNhanHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(txtTenNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)
                    .addComponent(txtDiaChiNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(cbbTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49)
                    .addComponent(txtSdtNgNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemHD)
                    .addComponent(btnSuaHD)
                    .addComponent(btnXoaHD)
                    .addComponent(btnLamMoiHD))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa đơn", jPanel14);

        jLabel35.setText("Id");

        lblIdNV.setText("-");

        jLabel39.setText("Ten");

        jLabel44.setText("Ten dem");

        jLabel48.setText("Ma");

        jLabel50.setText("Gioi tinh");

        jLabel51.setText("Ngay sinh");

        jLabel52.setText("Ho");

        jLabel53.setText("Mat khau");

        jLabel54.setText("IdGuiBC");

        jLabel55.setText("Trang thai");

        jLabel56.setText("Dia chi");

        jLabel57.setText("Sdt");

        btnThemNV.setText("Them");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        btnSuaNV.setText("Sua");
        btnSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNVActionPerformed(evt);
            }
        });

        btnXoaNV.setText("Xoa");
        btnXoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNVActionPerformed(evt);
            }
        });

        btnLamMoiNV.setText("Lam moi");
        btnLamMoiNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiNVActionPerformed(evt);
            }
        });

        tblNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Ma", "Ten", "Ten dem", "Ho", "Dia chi", "Trang thai", "Sdt", "Gioi tinh", "Ngay sinh", "Mat khau", "Ten CH", "Ten CV"
            }
        ));
        tblNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNVMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblNV);

        buttonGroup1.add(radioNam);
        radioNam.setText("Nam");

        buttonGroup1.add(radioNu);
        radioNu.setText("Nu");

        jLabel59.setText("Ma CH");

        jLabel60.setText("Ma CV");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnThemNV)
                        .addGap(132, 132, 132)
                        .addComponent(btnSuaNV)
                        .addGap(160, 160, 160)
                        .addComponent(btnXoaNV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLamMoiNV)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 798, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(35, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel39)
                            .addComponent(jLabel44)
                            .addComponent(jLabel52)
                            .addComponent(jLabel55)
                            .addComponent(jLabel56)
                            .addComponent(jLabel57))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(txtSdtNV, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel60))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(txtDiaChiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel54))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoNV, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblIdNV, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTenDemNV)
                                    .addComponent(txtTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgSinhNV)
                            .addComponent(txtMatKhauNV)
                            .addComponent(txtMaNV)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(radioNam)
                                .addGap(18, 18, 18)
                                .addComponent(radioNu))
                            .addComponent(lblIdGuiBC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbCV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbCH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(lblIdNV)
                    .addComponent(jLabel48)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel50)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioNam)
                    .addComponent(radioNu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jLabel51)
                    .addComponent(txtNgSinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenDemNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel52)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtHoNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel53)
                        .addComponent(txtMatKhauNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jLabel56)
                    .addComponent(txtDiaChiNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdGuiBC))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59)
                    .addComponent(cbbCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(txtSdtNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60)
                    .addComponent(cbbCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNV)
                    .addComponent(btnSuaNV)
                    .addComponent(btnXoaNV)
                    .addComponent(btnLamMoiNV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 845, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Nhân viên", jPanel15);

        jLabel58.setText("Id");

        lblIdCTSP.setText("-");

        jLabel61.setText("San pham");

        jLabel62.setText("Nha SX");

        jLabel66.setText("Nam BH");

        jLabel69.setText("Mo ta");

        jLabel70.setText("So luong ton");

        btnThemCTSP.setText("Them");
        btnThemCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTSPActionPerformed(evt);
            }
        });

        btnSuaCTSP.setText("Sua");
        btnSuaCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTSPActionPerformed(evt);
            }
        });

        btnXoaCTSP.setText("Xoa");
        btnXoaCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTSPActionPerformed(evt);
            }
        });

        btnLamMoiCTSP.setText("Lam moi");
        btnLamMoiCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiCTSPActionPerformed(evt);
            }
        });

        tblCTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "San pham", "Nha SX", "Mau sac", "Dong SP", "Nam BH", "Gia nhap", "Gia ban", "Mo ta"
            }
        ));
        tblCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTSPMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblCTSP);

        jLabel63.setText("Mau sac");

        jLabel64.setText("Dong SP");

        jLabel67.setText("Gia nhap");

        jLabel71.setText("Gia ban");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnThemCTSP)
                        .addGap(132, 132, 132)
                        .addComponent(btnSuaCTSP)
                        .addGap(160, 160, 160)
                        .addComponent(btnXoaCTSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLamMoiCTSP)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel58)
                                    .addComponent(jLabel61)
                                    .addComponent(jLabel62)
                                    .addComponent(jLabel66)
                                    .addComponent(jLabel69)
                                    .addComponent(jLabel70))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(cbbNSX, javax.swing.GroupLayout.Alignment.LEADING, 0, 120, Short.MAX_VALUE)
                                                .addComponent(cbbSP, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNamBHCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblIdCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(86, 86, 86)
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                                                .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jLabel64)
                                            .addComponent(jLabel63))
                                        .addGap(47, 47, 47)
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbbDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtGiaNhapCTSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtGiaBanCTSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtMoTaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 798, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(35, Short.MAX_VALUE))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(lblIdCTSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(cbbSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63)
                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel62)
                            .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel64))))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel66)
                        .addComponent(txtNamBHCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtGiaNhapCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel67)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel70)
                        .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtGiaBanCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(txtMoTaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemCTSP)
                    .addComponent(btnSuaCTSP)
                    .addComponent(btnXoaCTSP)
                    .addComponent(btnLamMoiCTSP))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 845, Short.MAX_VALUE)
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel17Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel17Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("ChiTietSP", jPanel17);

        jLabel76.setText("Ma HD");

        jLabel77.setText("So luong");

        jLabel82.setText("Don gia");

        btnThemHDCT.setText("Them");
        btnThemHDCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHDCTActionPerformed(evt);
            }
        });

        btnSuaHDCT.setText("Sua");
        btnSuaHDCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaHDCTActionPerformed(evt);
            }
        });

        btnXoaHDCT.setText("Xoa");
        btnXoaHDCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHDCTActionPerformed(evt);
            }
        });

        btnClearHDCT.setText("Lam moi");
        btnClearHDCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearHDCTActionPerformed(evt);
            }
        });

        tblHoaDOnCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã HD", "Mã SP", "Số lượng", "Đơn giá"
            }
        ));
        tblHoaDOnCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDOnCTMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblHoaDOnCT);

        cbbMaHDCT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblMaSP.setText("Ma SP");

        cbbMaSPHDCT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                                .addComponent(btnThemHDCT)
                                .addGap(49, 49, 49)
                                .addComponent(btnSuaHDCT)
                                .addGap(53, 53, 53)
                                .addComponent(btnXoaHDCT)
                                .addGap(69, 69, 69)
                                .addComponent(btnClearHDCT)))
                        .addContainerGap(89, Short.MAX_VALUE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel82)
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel77, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbMaSPHDCT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSoLuongHDCT)
                            .addComponent(txtDonGiaHDCT, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(cbbMaHDCT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel76)
                    .addComponent(cbbMaHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMaSP)
                    .addComponent(cbbMaSPHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(txtSoLuongHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(txtDonGiaHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemHDCT)
                    .addComponent(btnSuaHDCT)
                    .addComponent(btnXoaHDCT)
                    .addComponent(btnClearHDCT))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 845, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 239, Short.MAX_VALUE)))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Hoa Don CT", jPanel21);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTSPMouseClicked
        int index = tblCTSP.getSelectedRow();
        fillDataCTSP(index);
    }//GEN-LAST:event_tblCTSPMouseClicked

    private void btnLamMoiCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiCTSPActionPerformed

    }//GEN-LAST:event_btnLamMoiCTSPActionPerformed

    private void btnXoaCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTSPActionPerformed
        ChiTietSP chiTietSP = this.getChiTietSP();
        String id = lblIdCTSP.getText();
        chiTietSP.setId(id);
        String mess = "Xoa " + this.chiTietSPService.delete(chiTietSP);
        JOptionPane.showMessageDialog(this, mess);
        loadTableChiTietSP();
    }//GEN-LAST:event_btnXoaCTSPActionPerformed

    private void btnSuaCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTSPActionPerformed
        ChiTietSP chiTietSP = this.getChiTietSP();
        String id = lblIdCTSP.getText();
        chiTietSP.setId(id);
        String mess = "Sua " + this.chiTietSPService.addOrUpdate(chiTietSP);
        JOptionPane.showMessageDialog(this, mess);
        loadTableChiTietSP();
    }//GEN-LAST:event_btnSuaCTSPActionPerformed

    private void btnThemCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTSPActionPerformed
        ChiTietSP chiTietSP = this.getChiTietSP();
        String mess = "Them " + this.chiTietSPService.addOrUpdate(chiTietSP);
        JOptionPane.showMessageDialog(this, mess);
        loadTableChiTietSP();
    }//GEN-LAST:event_btnThemCTSPActionPerformed

    private void tblNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNVMouseClicked
        int index = tblNV.getSelectedRow();
        fillDataNhanVien(index);
    }//GEN-LAST:event_tblNVMouseClicked

    private void btnLamMoiNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoiNVActionPerformed

    private void btnXoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNVActionPerformed
        NhanVien nhanVien = null;
        try {
            nhanVien = this.getNhanVien();
            String id = lblIdNV.getText();
            nhanVien.setId(id);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        String mess = "Xoa " + this.nhanVienService.delete(nhanVien);
        JOptionPane.showMessageDialog(this, mess);
        //        clearData(lblIdkhachHang, txtMaKhachHang, txtTenKhachHang);
        loadTableNhanVien();
    }//GEN-LAST:event_btnXoaNVActionPerformed

    private void btnSuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNVActionPerformed
        try {
            NhanVien nhanVien = this.getNhanVien();
            String id = lblIdNV.getText().trim();
            nhanVien.setId(id);
            String mess = "Sua " + this.nhanVienService.addOrUpdate(nhanVien);
            JOptionPane.showMessageDialog(this, mess);
            loadTableNhanVien();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaNVActionPerformed

    //CRUD NHÂN VIÊN
    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        try {
            NhanVien nhanVien = this.getNhanVien();
            System.out.println(nhanVien.getTrangThai());
            String mess = "Them " + this.nhanVienService.addOrUpdate(nhanVien);
            JOptionPane.showMessageDialog(this, mess);
            loadTableNhanVien();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void tblHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDMouseClicked
        int index = tblHD.getSelectedRow();
        fillDataHoaDon(index);
    }//GEN-LAST:event_tblHDMouseClicked

    private void btnLamMoiHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoiHDActionPerformed

    private void btnXoaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHDActionPerformed
        try {
            HoaDon hoaDon = this.getHoaDon();
            String id = lblIdHD.getText();
            hoaDon.setId(id);
            String mess = "Xoa " + this.hdService.delete(hoaDon);
            JOptionPane.showMessageDialog(this, mess);
            //        clearData(lblIdkhachHang, txtMaKhachHang, txtTenKhachHang);
            loadTableHoaDon();
        } catch (ParseException ex) {
            Logger.getLogger(DuAnMau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXoaHDActionPerformed

    private void btnSuaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaHDActionPerformed
        try {
            HoaDon hoaDon = this.getHoaDon();
            String id = lblIdHD.getText().trim();
            hoaDon.setId(id);
            String mess = "Sua " + this.hdService.addOrUpdate(hoaDon);
            JOptionPane.showMessageDialog(this, mess);
            //            clearData(lblIdkhachHang, txtMaKhachHang, txtTenKhachHang);
            loadTableHoaDon();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaHDActionPerformed

    //CRUD HOÁ ĐƠN
    private void btnThemHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHDActionPerformed
        try {
            HoaDon hoaDon = this.getHoaDon();
            String mess = "Them " + this.hdService.addOrUpdate(hoaDon);
            JOptionPane.showMessageDialog(this, mess);
            loadTableHoaDon();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnThemHDActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        int index = tblKhachHang.getSelectedRow();
        fillDataKhachHang(index);
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnClearKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearKHActionPerformed
        //        clearData(lblIdSp, txtMaSp, txtTenSp);
    }//GEN-LAST:event_btnClearKHActionPerformed

    private void btnXoaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKHActionPerformed
        try {
            KhachHang khachHang = this.getKhachHang();
            String id = lblIdkhachHang.getText();
            khachHang.setId(id);
            String mess = "Xoa " + this.khachHangService.delete(khachHang);
            JOptionPane.showMessageDialog(this, mess);
            clearData(lblIdkhachHang, txtMaKhachHang, txtTenKhachHang);
            loadTableKhachHang();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaKHActionPerformed

    private void btnSuaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKHActionPerformed
        try {
            KhachHang khachHang = this.getKhachHang();
            String id = lblIdkhachHang.getText().trim();
            khachHang.setId(id);
            String mess = "Sua " + this.khachHangService.addOrUpdate(khachHang);
            JOptionPane.showMessageDialog(this, mess);
            clearData(lblIdkhachHang, txtMaKhachHang, txtTenKhachHang);
            loadTableKhachHang();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaKHActionPerformed

    // CRUD Khách Hàng

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        try {
            KhachHang khachHang = this.getKhachHang();
            String mess = "Them " + this.khachHangService.addOrUpdate(khachHang);
            JOptionPane.showMessageDialog(this, mess);
            loadTableKhachHang();
            clearData(lblIdkhachHang, txtMaKhachHang, txtTenKhachHang);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void tblCuaHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCuaHangMouseClicked
        int index = tblCuaHang.getSelectedRow();
        fillDataCuaHang(index);
    }//GEN-LAST:event_tblCuaHangMouseClicked

    private void btnClearCuaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCuaHangActionPerformed
        //        clearData(lblIdSp, txtMaSp, txtTenSp);
    }//GEN-LAST:event_btnClearCuaHangActionPerformed

    private void btnXoaCuaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCuaHangActionPerformed
        CuaHang cuaHang = this.getCuaHang();
        String id = lblIdCuaHang.getText();
        cuaHang.setId(id);
        String mess = "Xoa " + this.cuaHangService.delete(cuaHang);
        JOptionPane.showMessageDialog(this, mess);
        loadTableCuaHang();
        clearData(lblIdCuaHang, txtMaCuaHang, txtTenCuaHang);
    }//GEN-LAST:event_btnXoaCuaHangActionPerformed

    private void btnSuaCuaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCuaHangActionPerformed
        CuaHang cuaHang = this.getCuaHang();
        String id = lblIdCuaHang.getText().trim();
        cuaHang.setId(id);
        String mess = "Sua " + this.cuaHangService.addOrUpdate(cuaHang);
        JOptionPane.showMessageDialog(this, mess);
        loadTableCuaHang();
        clearData(lblIdCuaHang, txtMaCuaHang, txtTenCuaHang);
    }//GEN-LAST:event_btnSuaCuaHangActionPerformed

    // CRUD Cửa Hàng 

    private void btnThemCuaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCuaHangActionPerformed
        CuaHang cuaHang = this.getCuaHang();
        String mess = "Them " + this.cuaHangService.addOrUpdate(cuaHang);
        JOptionPane.showMessageDialog(this, mess);
        loadTableCuaHang();
        clearData(lblIdCuaHang, txtMaCuaHang, txtTenCuaHang);
    }//GEN-LAST:event_btnThemCuaHangActionPerformed

    private void tblSpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSpMouseClicked
        int index = tblSp.getSelectedRow();
        fillDataSanPham(index);
    }//GEN-LAST:event_tblSpMouseClicked

    private void btnClearSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSpActionPerformed
        clearData(lblIdSp, txtMaSp, txtTenSp);
    }//GEN-LAST:event_btnClearSpActionPerformed

    private void btnXoaSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSpActionPerformed
        SanPham sanPham = this.getSanPham();
        String id = lblIdSp.getText();
        sanPham.setId(id);
        String mess = "Xoa " + this.sanPhamService.delete(sanPham);
        JOptionPane.showMessageDialog(this, mess);
        loadTableSanPham();
        clearData(lblIdSp, txtMaSp, txtTenSp);
    }//GEN-LAST:event_btnXoaSpActionPerformed

    private void btnSuaSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSpActionPerformed
        SanPham sanPham = this.getSanPham();
        String id = lblIdSp.getText().trim();
        sanPham.setId(id);
        String mess = "Sua " + this.sanPhamService.addOrUpdate(sanPham);
        JOptionPane.showMessageDialog(this, mess);
        loadTableSanPham();
        clearData(lblIdSp, txtMaSp, txtTenSp);
    }//GEN-LAST:event_btnSuaSpActionPerformed

    //    CRUD Sản Phẩm

    private void btnThemSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSpActionPerformed
        SanPham sanPham = this.getSanPham();
        String id = lblIdSp.getText().trim();
        String mess = "Them " + this.sanPhamService.addOrUpdate(sanPham);
        JOptionPane.showMessageDialog(this, mess);
        loadTableSanPham();
        clearData(lblIdSp, txtMaSp, txtTenSp);
    }//GEN-LAST:event_btnThemSpActionPerformed

    private void tblNsxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNsxMouseClicked
        int index = tblNsx.getSelectedRow();
        fillDataNhaSx(index);
    }//GEN-LAST:event_tblNsxMouseClicked

    private void btnClearNsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearNsxActionPerformed
        clearData(lblIdNsx, txtMaNsx, txtTenNsx);
    }//GEN-LAST:event_btnClearNsxActionPerformed

    private void btnXoaNsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNsxActionPerformed
        NhaSanXuat nhaSanXuat = this.getNhaSanXuat();
        String id = lblIdNsx.getText();
        nhaSanXuat.setId(id);
        String mess = "Xoa " + this.nsxService.delete(nhaSanXuat);
        JOptionPane.showMessageDialog(this, mess);
        loadTableNhaSx();
        clearData(lblIdNsx, txtMaNsx, txtTenNsx);
    }//GEN-LAST:event_btnXoaNsxActionPerformed

    private void btnSuaNsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNsxActionPerformed
        NhaSanXuat nhaSanXuat = this.getNhaSanXuat();
        String id = lblIdNsx.getText().trim();
        nhaSanXuat.setId(id);
        String mess = "Sua " + this.nsxService.addOrUpdate(nhaSanXuat);
        JOptionPane.showMessageDialog(this, mess);
        loadTableNhaSx();
        clearData(lblIdNsx, txtMaNsx, txtTenNsx);
    }//GEN-LAST:event_btnSuaNsxActionPerformed

//    CRUD Nhà Sản Xuất

    private void btnThemNsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNsxActionPerformed
        NhaSanXuat nhaSanXuat = this.getNhaSanXuat();
        String id = lblIdNsx.getText().trim();
        String mess = "Them " + this.nsxService.addOrUpdate(nhaSanXuat);
        JOptionPane.showMessageDialog(this, mess);
        loadTableNhaSx();
        clearData(lblIdNsx, txtMaNsx, txtTenNsx);
    }//GEN-LAST:event_btnThemNsxActionPerformed

    // CRUD Chức Vụ

    private void tblChucVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChucVuMouseClicked
        int index = tblChucVu.getSelectedRow();
        fillDataChucVu(index);
    }//GEN-LAST:event_tblChucVuMouseClicked

    private void btnClearChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearChucVuActionPerformed
        clearData(lblIdChucVu, txtMaChucVu, txtTenChucVu);
    }//GEN-LAST:event_btnClearChucVuActionPerformed

    private void btnXoaChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaChucVuActionPerformed
        ChucVu chucVu = this.getChucVu();
        String id = lblIdChucVu.getText();
        chucVu.setId(id);
        String mess = "Xoa " + this.chucVuService.delete(chucVu);
        JOptionPane.showMessageDialog(this, mess);
        loadTableChucVu();
        clearData(lblIdChucVu, txtMaChucVu, txtTenChucVu);
    }//GEN-LAST:event_btnXoaChucVuActionPerformed

    private void btnSuaChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaChucVuActionPerformed
        String id = lblIdChucVu.getText().trim();
        ChucVu chucVu = this.getChucVu();
        chucVu.setId(id);
        String mess = "Sua " + this.chucVuService.addOrUpdate(chucVu);
        JOptionPane.showMessageDialog(this, mess);
        loadTableChucVu();
        clearData(lblIdChucVu, txtMaChucVu, txtTenChucVu);
    }//GEN-LAST:event_btnSuaChucVuActionPerformed

    private void btnThemChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemChucVuActionPerformed
        ChucVu chucVu = this.getChucVu();
        String mess = "Them " + this.chucVuService.addOrUpdate(chucVu);
        JOptionPane.showMessageDialog(this, mess);
        loadTableChucVu();
        clearData(lblIdChucVu, txtMaChucVu, txtTenChucVu);
    }//GEN-LAST:event_btnThemChucVuActionPerformed

    //CRUD bảng Màu Sắc

    private void tblMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseClicked
        int index = tblMauSac.getSelectedRow();
        fillDataMauSac(index);
    }//GEN-LAST:event_tblMauSacMouseClicked

    private void btnClearMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearMauSacActionPerformed
        clearData(lblIdMauSac, txtMaMau, txtTenMau);
    }//GEN-LAST:event_btnClearMauSacActionPerformed

    private void btnXoaMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMauSacActionPerformed
        MauSac mauSac = this.getMauSac();
        String id = lblIdMauSac.getText();
        mauSac.setId(id);
        String mess = "Xoa " + this.mauSacService.delete(mauSac);
        JOptionPane.showMessageDialog(this, mess);
        loadTableMauSac();
        clearData(lblIdMauSac, txtMaMau, txtTenMau);
    }//GEN-LAST:event_btnXoaMauSacActionPerformed

    private void btnSuaMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaMauSacActionPerformed
        String id = lblIdMauSac.getText().trim();
        MauSac mauSac = this.getMauSac();
        mauSac.setId(id);
        String mess = "Sua " + this.mauSacService.addOrUpdate(mauSac);
        JOptionPane.showMessageDialog(this, mess);
        loadTableMauSac();
        clearData(lblIdMauSac, txtMaMau, txtTenMau);
    }//GEN-LAST:event_btnSuaMauSacActionPerformed

    private void btnThemMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMauSacActionPerformed
        MauSac mauSac = this.getMauSac();
        String mess = "Them " + this.mauSacService.addOrUpdate(mauSac);
        JOptionPane.showMessageDialog(this, mess);
        loadTableMauSac();
        clearData(lblIdMauSac, txtMaMau, txtTenMau);
    }//GEN-LAST:event_btnThemMauSacActionPerformed

    //-- CRUD Bảng Dòng Sản Phẩm

    private void tblDongSpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDongSpMouseClicked
        int index = tblDongSp.getSelectedRow();
        fillDataDongSP(index);
    }//GEN-LAST:event_tblDongSpMouseClicked

    private void btnClearDongSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearDongSpActionPerformed
        clearData(lblIdDongSp, txtMaDongSp, txtTenDongSp);
    }//GEN-LAST:event_btnClearDongSpActionPerformed

    private void btnXoaDongSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDongSpActionPerformed
        DongSP dongSP = this.getDOngSP();
        String id = lblIdDongSp.getText();
        dongSP.setId(id);
        String mess = "Xoa " + this.dongSpService.delete(dongSP);
        JOptionPane.showMessageDialog(this, mess);
        loadTableDongSP();
        clearData(lblIdDongSp, txtMaDongSp, txtTenDongSp);
    }//GEN-LAST:event_btnXoaDongSpActionPerformed

    private void btnSuaDongSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDongSpActionPerformed
        String id = lblIdDongSp.getText().trim();
        DongSP dongSP = this.getDOngSP();
        dongSP.setId(id);
        String mess = "Sua " + this.dongSpService.addOrUpdate(dongSP);
        JOptionPane.showMessageDialog(this, mess);
        loadTableDongSP();
        clearData(lblIdDongSp, txtMaDongSp, txtTenDongSp);
    }//GEN-LAST:event_btnSuaDongSpActionPerformed

    private void btnThemDongSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDongSpActionPerformed
        DongSP dongSP = this.getDOngSP();
        String mess = "Them " + this.dongSpService.addOrUpdate(dongSP);
        JOptionPane.showMessageDialog(this, mess);
        loadTableDongSP();
        clearData(lblIdDongSp, txtMaDongSp, txtTenDongSp);
    }//GEN-LAST:event_btnThemDongSpActionPerformed

    private void btnThemHDCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHDCTActionPerformed
        HoaDonChiTiet hoaDonChiTiet = this.getHoaDonCT();
        String mess = "Them " + this.hoaDonCTService.addOrUpdate(hoaDonChiTiet);
        JOptionPane.showMessageDialog(this, mess);
        loadTableHoaDonChiTiet();
    }//GEN-LAST:event_btnThemHDCTActionPerformed

    private void btnSuaHDCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaHDCTActionPerformed
        HoaDonChiTiet hoaDonChiTiet = this.getHoaDonCT();
        String mess = "Sua " + this.hoaDonCTService.addOrUpdate(hoaDonChiTiet);
        JOptionPane.showMessageDialog(this, mess);
        loadTableHoaDonChiTiet();
    }//GEN-LAST:event_btnSuaHDCTActionPerformed

    private void btnXoaHDCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHDCTActionPerformed
        HoaDonChiTiet hoaDonChiTiet = this.getHoaDonCT();
        String mess = "Xoa " + this.hoaDonCTService.delete(hoaDonChiTiet);
        JOptionPane.showMessageDialog(this, mess);
        loadTableHoaDonChiTiet();
    }//GEN-LAST:event_btnXoaHDCTActionPerformed

    private void btnClearHDCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearHDCTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearHDCTActionPerformed

    private void tblHoaDOnCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDOnCTMouseClicked
        int index = tblHoaDOnCT.getSelectedRow();
        fillDataHoaDonCT(index);
    }//GEN-LAST:event_tblHoaDOnCTMouseClicked

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(DuAnMau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DuAnMau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DuAnMau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DuAnMau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new DuAnMau().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearChucVu;
    private javax.swing.JButton btnClearCuaHang;
    private javax.swing.JButton btnClearDongSp;
    private javax.swing.JButton btnClearHDCT;
    private javax.swing.JButton btnClearKH;
    private javax.swing.JButton btnClearMauSac;
    private javax.swing.JButton btnClearNsx;
    private javax.swing.JButton btnClearSp;
    private javax.swing.JButton btnLamMoiCTSP;
    private javax.swing.JButton btnLamMoiHD;
    private javax.swing.JButton btnLamMoiNV;
    private javax.swing.JButton btnSuaCTSP;
    private javax.swing.JButton btnSuaChucVu;
    private javax.swing.JButton btnSuaCuaHang;
    private javax.swing.JButton btnSuaDongSp;
    private javax.swing.JButton btnSuaHD;
    private javax.swing.JButton btnSuaHDCT;
    private javax.swing.JButton btnSuaKH;
    private javax.swing.JButton btnSuaMauSac;
    private javax.swing.JButton btnSuaNV;
    private javax.swing.JButton btnSuaNsx;
    private javax.swing.JButton btnSuaSp;
    private javax.swing.JButton btnThemCTSP;
    private javax.swing.JButton btnThemChucVu;
    private javax.swing.JButton btnThemCuaHang;
    private javax.swing.JButton btnThemDongSp;
    private javax.swing.JButton btnThemHD;
    private javax.swing.JButton btnThemHDCT;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnThemMauSac;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnThemNsx;
    private javax.swing.JButton btnThemSp;
    private javax.swing.JButton btnXoaCTSP;
    private javax.swing.JButton btnXoaChucVu;
    private javax.swing.JButton btnXoaCuaHang;
    private javax.swing.JButton btnXoaDongSp;
    private javax.swing.JButton btnXoaHD;
    private javax.swing.JButton btnXoaHDCT;
    private javax.swing.JButton btnXoaKH;
    private javax.swing.JButton btnXoaMauSac;
    private javax.swing.JButton btnXoaNV;
    private javax.swing.JButton btnXoaNsx;
    private javax.swing.JButton btnXoaSp;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbCH;
    private javax.swing.JComboBox<String> cbbCV;
    private javax.swing.JComboBox<String> cbbDongSP;
    private javax.swing.JComboBox<String> cbbKH;
    private javax.swing.JComboBox<String> cbbMaHDCT;
    private javax.swing.JComboBox<String> cbbMaSPHDCT;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbNSX;
    private javax.swing.JComboBox<String> cbbNV;
    private javax.swing.JComboBox<String> cbbQuocGiaCuaHang;
    private javax.swing.JComboBox<String> cbbQuocGiaKH;
    private javax.swing.JComboBox<String> cbbSP;
    private javax.swing.JComboBox<String> cbbTinhTrang;
    private javax.swing.JComboBox<String> cbbTpCuaHang;
    private javax.swing.JComboBox<String> cbbTpKH;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblIdCTSP;
    private javax.swing.JLabel lblIdChucVu;
    private javax.swing.JLabel lblIdCuaHang;
    private javax.swing.JLabel lblIdDongSp;
    private javax.swing.JLabel lblIdGuiBC;
    private javax.swing.JLabel lblIdHD;
    private javax.swing.JLabel lblIdMauSac;
    private javax.swing.JLabel lblIdNV;
    private javax.swing.JLabel lblIdNsx;
    private javax.swing.JLabel lblIdSp;
    private javax.swing.JLabel lblIdkhachHang;
    private javax.swing.JLabel lblMaSP;
    private javax.swing.JRadioButton radioNam;
    private javax.swing.JRadioButton radioNu;
    private javax.swing.JTable tblCTSP;
    private javax.swing.JTable tblChucVu;
    private javax.swing.JTable tblCuaHang;
    private javax.swing.JTable tblDongSp;
    private javax.swing.JTable tblHD;
    private javax.swing.JTable tblHoaDOnCT;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblMauSac;
    private javax.swing.JTable tblNV;
    private javax.swing.JTable tblNsx;
    private javax.swing.JTable tblSp;
    private javax.swing.JTextField txtDiaChiCuaHang;
    private javax.swing.JTextField txtDiaChiKH;
    private javax.swing.JTextField txtDiaChiNV;
    private javax.swing.JTextField txtDiaChiNguoiNhan;
    private javax.swing.JTextField txtDonGiaHDCT;
    private javax.swing.JTextField txtGiaBanCTSP;
    private javax.swing.JTextField txtGiaNhapCTSP;
    private javax.swing.JTextField txtHoKH;
    private javax.swing.JTextField txtHoNV;
    private javax.swing.JTextField txtMaChucVu;
    private javax.swing.JTextField txtMaCuaHang;
    private javax.swing.JTextField txtMaDongSp;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtMaMau;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaNsx;
    private javax.swing.JTextField txtMaSp;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtMatKhauNV;
    private javax.swing.JTextField txtMoTaCTSP;
    private javax.swing.JTextField txtNamBHCTSP;
    private javax.swing.JTextField txtNgSinh;
    private javax.swing.JTextField txtNgSinhNV;
    private javax.swing.JTextField txtNgayNhanHD;
    private javax.swing.JTextField txtNgayShip;
    private javax.swing.JTextField txtNgayTaoHD;
    private javax.swing.JTextField txtNgayThanhToanHD;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtSdtNV;
    private javax.swing.JTextField txtSdtNgNhan;
    private javax.swing.JTextField txtSoLuongHDCT;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtTenChucVu;
    private javax.swing.JTextField txtTenCuaHang;
    private javax.swing.JTextField txtTenDemKH;
    private javax.swing.JTextField txtTenDemNV;
    private javax.swing.JTextField txtTenDongSp;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenMau;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTenNguoiNhan;
    private javax.swing.JTextField txtTenNsx;
    private javax.swing.JTextField txtTenSp;
    // End of variables declaration//GEN-END:variables
}
