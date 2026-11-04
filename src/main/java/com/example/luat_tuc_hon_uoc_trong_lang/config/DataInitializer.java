package com.example.luat_tuc_hon_uoc_trong_lang.config;

import com.example.luat_tuc_hon_uoc_trong_lang.model.CustomRegulation;
import com.example.luat_tuc_hon_uoc_trong_lang.repository.CustomRegulationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Tự động khởi tạo dữ liệu mẫu ban đầu khi deploy lên môi trường Online
 * (nếu database chưa có dữ liệu nào)
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final CustomRegulationRepository repository;

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            log.info("Cơ sở dữ liệu đang trống. Đang tự động nạp dữ liệu di sản mẫu ban đầu...");

            CustomRegulation reg1 = CustomRegulation.builder()
                    .title("Khoán ước bảo vệ an ninh trật tự làng Mộ Trạch")
                    .villageName("Làng Mộ Trạch")
                    .commune("Xã Tân Hồng")
                    .district("Huyện Bình Giang")
                    .province("Tỉnh Hải Dương")
                    .historicalPeriod("Thời Lê sơ - Hậu Lê (Thế kỷ XV - XVIII)")
                    .category("Trật tự trị an")
                    .content("Điều 1: Trong làng trên dưới hòa thuận, người có chức dịch hay thường dân đều phải giữ nghiêm phép nước lệ làng.\n" +
                            "Điều 2: Ban đêm tuần đinh phải canh gác tại các điếm canh đầu thôn, đánh trống điểm canh, không để kẻ gian trà trộn trộm cắp hoa màu, gia súc.\n" +
                            "Điều 3: Con em trong làng theo nghiệp bút nghiên, đỗ đạt cử nhân, tiến sĩ được làng mở tiệc nghênh rước, cấp ruộng học điền để khuyến khích tinh thần hiếu học.")
                    .significance("Thể hiện tinh thần tự quản cộng đồng sâu sắc của làng xã Bắc Bộ, đề cao tinh thần cảnh giác bảo vệ trị an thôn xóm và truyền thống hiếu học độc đáo.")
                    .sourceReference("Bản khắc Hán Nôm lưu tại Đình Mộ Trạch, đối chiếu Viện Nghiên cứu Hán Nôm mã số HN-0125")
                    .build();

            CustomRegulation reg2 = CustomRegulation.builder()
                    .title("Hương ước làng Quỳnh Đôi về việc khuyến học và tế lễ khoa cử")
                    .villageName("Thôn Quỳnh Đôi")
                    .commune("Xã Quỳnh Đôi")
                    .district("Huyện Quỳnh Lưu")
                    .province("Tỉnh Nghệ An")
                    .historicalPeriod("Thời Nguyễn (1802 - 1945)")
                    .category("Đạo đức xã hội")
                    .content("Người trong làng từ 7 tuổi trở lên đều cho đến trường học chữ. Ai đỗ Tú tài, Cử nhân, Tiến sĩ thì làng cử kỳ mục mang cờ quạt đón rước tại cổng làng, cấp 3 sào ruộng học điền để động viên hậu thế noi gương lập thân lập nghiệp.")
                    .significance("Minh chứng tiêu biểu cho truyền thống hiếu học xứ Nghệ và tinh thần trọng đạo lý, đề cao nhân tài của văn hóa làng xã Việt Nam.")
                    .sourceReference("Địa chí làng Quỳnh Đôi - NXB Nghệ An")
                    .build();

            repository.save(reg1);
            repository.save(reg2);

            log.info("Đã nạp thành công {} bản ghi di sản mẫu vào database!", repository.count());
        }
    }
}
