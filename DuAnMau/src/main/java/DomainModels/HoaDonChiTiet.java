
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HoaDonChiTiet")
@IdClass(HoaDonChiTietId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonChiTiet implements Serializable{
    
    @Id
    @ManyToOne
    @JoinColumn(name = "IdHoaDon", nullable = false)
    private HoaDon idHoaDon;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "IdChiTietSP", nullable = false)
    private ChiTietSP idChiTietSP;
    
    @Column(name = "SoLuong")
    private int soLuong;
    
    @Column(name = "DonGia")
    private BigDecimal donGia;
    
}
