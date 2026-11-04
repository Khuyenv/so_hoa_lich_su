package com.example.luat_tuc_hon_uoc_trong_lang.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Entity lưu trữ thông tin số hóa di sản: "Luật tục và hương ước làng xã" (Mã C05)
 */
@Entity
@Table(name = "custom_regulations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomRegulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Tiêu đề điều khoản hoặc hương ước */
    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(max = 255, message = "Tiêu đề không được vượt quá 255 ký tự")
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    /** Tên làng/thôn */
    @NotBlank(message = "Tên làng/thôn không được để trống")
    @Size(max = 150, message = "Tên làng/thôn không được vượt quá 150 ký tự")
    @Column(name = "village_name", nullable = false, length = 150)
    private String villageName;

    /** Xã/Phường */
    @NotBlank(message = "Xã/Phường không được để trống")
    @Size(max = 150, message = "Xã/Phường không được vượt quá 150 ký tự")
    @Column(name = "commune", nullable = false, length = 150)
    private String commune;

    /** Huyện/Quận */
    @NotBlank(message = "Huyện/Quận không được để trống")
    @Size(max = 150, message = "Huyện/Quận không được vượt quá 150 ký tự")
    @Column(name = "district", nullable = false, length = 150)
    private String district;

    /** Tỉnh/Thành phố */
    @NotBlank(message = "Tỉnh/Thành phố không được để trống")
    @Size(max = 150, message = "Tỉnh/Thành phố không được vượt quá 150 ký tự")
    @Column(name = "province", nullable = false, length = 150)
    private String province;

    /** Giai đoạn lịch sử (Thời Lê, Thời Nguyễn,...) */
    @NotBlank(message = "Vui lòng chọn hoặc nhập giai đoạn lịch sử")
    @Size(max = 100, message = "Giai đoạn lịch sử không được vượt quá 100 ký tự")
    @Column(name = "historical_period", nullable = false, length = 100)
    private String historicalPeriod;

    /** Phân loại nhóm (Trật tự trị an, Đạo đức xã hội, Tín ngưỡng - Lễ hội, Kinh tế - Sản xuất) */
    @NotBlank(message = "Vui lòng chọn phân loại nhóm")
    @Size(max = 100, message = "Phân loại nhóm không được vượt quá 100 ký tự")
    @Column(name = "category", nullable = false, length = 100)
    private String category;

    /** Nội dung chi tiết luật tục/hương ước */
    @NotBlank(message = "Nội dung chi tiết luật tục/hương ước không được để trống")
    @Lob
    @Column(name = "content", columnDefinition = "NVARCHAR(MAX)", nullable = false)
    private String content;

    /** Ý nghĩa văn hóa & lịch sử */
    @Lob
    @Column(name = "significance", columnDefinition = "NVARCHAR(MAX)")
    private String significance;

    /** Nguồn gốc tài liệu / Dẫn chứng */
    @Size(max = 500, message = "Nguồn gốc tài liệu không được vượt quá 500 ký tự")
    @Column(name = "source_reference", length = 500)
    private String sourceReference;
}
