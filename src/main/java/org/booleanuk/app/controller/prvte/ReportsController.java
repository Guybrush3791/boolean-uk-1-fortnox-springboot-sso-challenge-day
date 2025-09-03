package org.booleanuk.app.controller.prvte;

import org.booleanuk.app.model.dto.report.CustomerValueResponseDto;
import org.booleanuk.app.model.dto.report.OrderValueResponseDto;
import org.booleanuk.app.model.dto.report.ProductSalesResponseDto;
import org.booleanuk.app.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/private/reports")
public class ReportsController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/customers/value")
    public List<CustomerValueResponseDto> customerValues() {
        return reportService.customerValues();
    }

    @GetMapping("/products/sales")
    public List<ProductSalesResponseDto> productSales() {
        return reportService.productSales();
    }

    @GetMapping("/orders/by-value")
    public List<OrderValueResponseDto> ordersByValue() {
        return reportService.ordersByValueDesc();
    }
}
