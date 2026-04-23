package re.cntt4.ss14.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import re.cntt4.ss14.repository.ITransactionRepository;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class TransactionController {
    private final ITransactionRepository transactionRepository;

    @GetMapping
    public String viewBanking() {
        return "bankingMoney";
    }

    @PostMapping("/banking")
    public String banking(
            @RequestParam(name = "senderId") Long senderId,
            @RequestParam(name = "receiveId") Long receiveId,
            @RequestParam(name = "amount") Double amount
    ) {
        transactionRepository.bankingMoney(senderId,receiveId,amount);
        return "result";
    }

    @GetMapping("/histories")
    public String histories(Model model) {
        model.addAttribute("histories",transactionRepository.getHistories());
        return "histories";
    }


}
