package com.nineya.haloplus.model.params;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.nineya.haloplus.model.dto.base.InputConverter;
import com.nineya.haloplus.model.entity.Log;
import com.nineya.haloplus.model.enums.LogType;

/**
 * @author johnniang
 * @date 19-4-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogParam implements InputConverter<Log> {

    @Size(max = 1023, message = "Length of log key must not be more than {max}")
    private String logKey;

    @NotNull(message = "Log type must not be null")
    private LogType type;

    @NotBlank(message = "Log content must not be blank")
    @Size(max = 1023, message = "Log content must not be more than 1023")
    private String content;

    private String ipAddress;

    public LogParam(String logKey, LogType type, String content) {
        this.logKey = logKey;
        this.type = type;
        this.content = content;
    }
}
