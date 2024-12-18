package com.example.soomsoom.domain.discord.init;

import com.example.soomsoom.domain.discord.eventListener.SoomsoomDiscordListener;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JdaInit {
    @Value("${discord.bot.token}")
    private String dbt;

    @PostConstruct
    public void jdaInit() {
        try {
            JDABuilder.createDefault(dbt)
                    .setActivity(Activity.playing("냐옹"))
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT,GatewayIntent.GUILD_MEMBERS)
                    .addEventListeners(new SoomsoomDiscordListener())
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Failed to initialize Discord bot", e);
        }
    }
}
