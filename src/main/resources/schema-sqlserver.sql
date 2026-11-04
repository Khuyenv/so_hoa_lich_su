-- ==============================================================
-- KỊCH BẢN TẠO DATABASE VÀ BẢNG TRÊN MS SQL SERVER (MÃ C05)
-- Đề tài: Số hóa di sản "Luật tục và hương ước làng xã"
-- ==============================================================

IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'luat_tuc_huong_uoc_db')
BEGIN
    CREATE DATABASE luat_tuc_huong_uoc_db COLLATE Vietnamese_CI_AS;
END
GO

USE luat_tuc_huong_uoc_db;
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'custom_regulations')
BEGIN
    CREATE TABLE custom_regulations (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        title NVARCHAR(255) NOT NULL,
        village_name NVARCHAR(150) NOT NULL,
        commune NVARCHAR(150) NOT NULL,
        district NVARCHAR(150) NOT NULL,
        province NVARCHAR(150) NOT NULL,
        historical_period NVARCHAR(100) NOT NULL,
        category NVARCHAR(100) NOT NULL,
        content NVARCHAR(MAX) NOT NULL,
        significance NVARCHAR(MAX) NULL,
        source_reference NVARCHAR(500) NULL
    );
END
GO

-- Xóa dữ liệu cũ nếu có
DELETE FROM custom_regulations;
GO

-- Thêm dữ liệu mẫu chuẩn tiếng Việt Unicode
INSERT INTO custom_regulations (title, village_name, commune, district, province, historical_period, category, content, significance, source_reference)
VALUES 
(
    N'Khoán ước bảo vệ an ninh trật tự làng Mộ Trạch', 
    N'Làng Mộ Trạch', 
    N'Xã Tân Hồng', 
    N'Huyện Bình Giang', 
    N'Tỉnh Hải Dương', 
    N'Thời Lê sơ - Hậu Lê (Thế kỷ XV - XVIII)', 
    N'Trật tự trị an', 
    N'Điều 1: Trong làng trên dưới hòa thuận, người có chức dịch hay thường dân đều phải giữ nghiêm phép nước lệ làng. Ban đêm tuần đinh phải canh gác tại các điếm canh đầu thôn, đánh trống điểm canh, không để kẻ gian trà trộn trộm cắp hoa màu, gia súc.', 
    N'Thể hiện tinh thần tự quản cộng đồng sâu sắc của làng xã Bắc Bộ, đề cao tinh thần cảnh giác bảo vệ trị an thôn xóm.', 
    N'Bản khắc Hán Nôm lưu tại Đình Mộ Trạch'
),
(
    N'Hương ước làng Quỳnh Đôi về việc khuyến học và tế lễ khoa cử', 
    N'Thôn Quỳnh Đôi', 
    N'Xã Quỳnh Đôi', 
    N'Huyện Quỳnh Lưu', 
    N'Tỉnh Nghệ An', 
    N'Thời Nguyễn (1802 - 1945)', 
    N'Đạo đức xã hội', 
    N'Người trong làng từ 7 tuổi trở lên đều cho đến trường học chữ. Ai đỗ Tú tài, Cử nhân, Tiến sĩ thì làng cử kỳ mục mang cờ quạt đón rước tại cổng làng, cấp 3 sào ruộng học điền để động viên hậu thế noi gương.', 
    N'Minh chứng tiêu biểu cho truyền thống hiếu học xứ Nghệ và tinh thần trọng đạo lý, nhân tài của văn hóa làng xã.', 
    N'Địa chí làng Quỳnh Đôi - NXB Nghệ An'
);
GO
