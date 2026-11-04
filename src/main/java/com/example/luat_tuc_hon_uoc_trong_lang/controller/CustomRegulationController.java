package com.example.luat_tuc_hon_uoc_trong_lang.controller;

import com.example.luat_tuc_hon_uoc_trong_lang.model.CustomRegulation;
import com.example.luat_tuc_hon_uoc_trong_lang.service.CustomRegulationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/customs")
@RequiredArgsConstructor
public class CustomRegulationController {

    private final CustomRegulationService customRegulationService;

    // Danh sách phân loại cố định theo yêu cầu đề bài
    @ModelAttribute("categories")
    public List<String> populateCategories() {
        return Arrays.asList(
                "Trật tự trị an",
                "Đạo đức xã hội",
                "Tín ngưỡng - Lễ hội",
                "Kinh tế - Sản xuất"
        );
    }

    // Danh sách giai đoạn lịch sử mẫu
    @ModelAttribute("historicalPeriods")
    public List<String> populateHistoricalPeriods() {
        return Arrays.asList(
                "Thời Lý - Trần (Thế kỷ XI - XIV)",
                "Thời Lê sơ - Hậu Lê (Thế kỷ XV - XVIII)",
                "Thời Tây Sơn (1778 - 1802)",
                "Thời Nguyễn (1802 - 1945)",
                "Thời kỳ Pháp thuộc (1884 - 1945)",
                "Thời kỳ Cận - Hiện đại"
        );
    }

    /**
     * Endpoint GET /customs/new: Hiển thị giao diện Form nhập liệu số hóa
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        if (!model.containsAttribute("regulation")) {
            model.addAttribute("regulation", new CustomRegulation());
        }
        return "customs/form";
    }

    /**
     * Endpoint POST /customs/save: Nhận dữ liệu từ form và lưu vào database
     */
    @PostMapping("/save")
    public String saveRegulation(@Valid @ModelAttribute("regulation") CustomRegulation regulation,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Vui lòng kiểm tra lại thông tin nhập liệu!");
            return "customs/form";
        }

        try {
            CustomRegulation saved = customRegulationService.saveRegulation(regulation);
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Đã số hóa thành công hương ước / luật tục: \"" + saved.getTitle() + "\" (Mã ID: #" + saved.getId() + ")");
            return "redirect:/customs";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi trong quá trình lưu dữ liệu: " + e.getMessage());
            return "customs/form";
        }
    }

    /**
     * Danh sách các luật tục / hương ước đã số hóa
     */
    @GetMapping
    public String listRegulations(@RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "category", required = false) String category,
                                  Model model) {
        List<CustomRegulation> list;
        if (keyword != null && !keyword.isBlank()) {
            list = customRegulationService.searchByKeyword(keyword);
            model.addAttribute("keyword", keyword);
        } else if (category != null && !category.isBlank()) {
            list = customRegulationService.filterByCategory(category);
            model.addAttribute("selectedCategory", category);
        } else {
            list = customRegulationService.getAllRegulations();
        }
        model.addAttribute("regulations", list);
        return "customs/list";
    }

    /**
     * Xem chi tiết một bản hương ước / luật tục
     */
    @GetMapping("/{id}")
    public String viewDetail(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        return customRegulationService.getRegulationById(id)
                .map(reg -> {
                    model.addAttribute("regulation", reg);
                    return "customs/detail";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy hương ước với ID: " + id);
                    return "redirect:/customs";
                });
    }

    /**
     * Sửa hương ước / luật tục đã lưu
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        return customRegulationService.getRegulationById(id)
                .map(reg -> {
                    model.addAttribute("regulation", reg);
                    model.addAttribute("isEdit", true);
                    return "customs/form";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy hương ước để chỉnh sửa.");
                    return "redirect:/customs";
                });
    }

    /**
     * Xóa hương ước
     */
    @PostMapping("/delete/{id}")
    public String deleteRegulation(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            customRegulationService.deleteRegulation(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa bản ghi số hóa thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa bản ghi: " + e.getMessage());
        }
        return "redirect:/customs";
    }
}
