package com.example.soomsoom.domain.discord.eventListener;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Slf4j
public class SoomsoomDiscordListener extends ListenerAdapter {
    private static final String TARGET_CHANNEL_ID = "1144254278068863079";

    // 멤버 입장 이벤트 처리
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Member member = event.getMember();
        TextChannel targetChannel = event.getGuild().getTextChannelById(TARGET_CHANNEL_ID);

        String welcomeMessage = "안녕하세요, " + member.getEffectiveName() + "님! 숨숨집 채널에 오신 것을 환영한다냥 🎉";
        targetChannel.sendMessage(welcomeMessage).queue(
                success -> log.info("환영 메시지를 성공적으로 전송했습니다."),
                error -> log.error("환영 메시지 전송 실패: {}", error.getMessage())
        );
    }
}
