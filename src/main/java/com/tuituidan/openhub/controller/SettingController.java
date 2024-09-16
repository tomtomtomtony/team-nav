package com.tuituidan.openhub.controller;

import com.tuituidan.openhub.bean.dto.SettingDto;
import com.tuituidan.openhub.bean.entity.EmailSetting;
import com.tuituidan.openhub.bean.entity.Setting;
import com.tuituidan.openhub.service.EmailSettingService;
import com.tuituidan.openhub.service.SettingService;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SettingController.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2021/9/27 0027
 */
@RestController
@RequestMapping("/api/v1")
public class SettingController {

    @Resource
    private SettingService settingService;

    @Resource
    private EmailSettingService emailSettingService;

    /**
     * 获取配置
     *
     * @return Setting
     */
    @GetMapping("/setting")
    public ResponseEntity<Setting> get() {
        return ResponseEntity.ok(settingService.get());
    }

    /**
     * 获取配置
     *
     * @return Setting
     */
    @GetMapping("/setting/email")
    public ResponseEntity<EmailSetting> getEmaiSetting() {
        return ResponseEntity.ok(emailSettingService.get());
    }

    /**
     * 保存配置
     *
     * @param settingDto settingDto
     * @return Void
     */
    @PatchMapping("/setting")
    public ResponseEntity<Void> saveSetting(@RequestBody SettingDto settingDto) {
        settingService.saveSetting(settingDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 保存配置
     *
     * @param setting setting
     * @return Void
     */
    @PatchMapping("/setting/email")
    public ResponseEntity<Void> saveEmailSetting(@RequestBody EmailSetting setting) {
        emailSettingService.saveSetting(setting);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 测试邮件
     *
     * @return Void
     */
    @PostMapping("/setting/email/test")
    public ResponseEntity<Void> testEmailSetting() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("这是一封测试邮件");
        EmailSetting setting = emailSettingService.get();
        message.setTo(setting.getUsername());
        emailSettingService.send(message);
        return ResponseEntity.noContent().build();
    }
}
