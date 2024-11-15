package starcode.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageBan extends ListenerAdapter {
    @Override
    public void onGuildBan(@NotNull GuildBanEvent event) {
        String reason = event.getGuild().retrieveBan(event.getUser()).complete().getReason(); // Pega o motivo do banimento
        MessageEmbed embed = new EmbedBuilder()
                .setThumbnail(event.getUser().getAvatarUrl())
                .setAuthor(event.getUser().getName(), null, event.getUser().getAvatarUrl())
                .setTitle("Banimento :no_entry_sign:")
                .setDescription("O usuário " + event.getUser().getAsMention() + " foi banido!")
                .addField("Motivo", reason != null ? reason : "Não especificado", false)
                .setFooter("ID do Usuário: " + event.getUser().getId(), null)
                .setTimestamp(java.time.OffsetDateTime.now().withNano(0).withSecond(0))
                .setColor(0xFF0000)
                .build();
        event.getGuild().getTextChannelById("1124423573994479737").sendMessageEmbeds(embed).queue(); // Envia a mensagem
    }
}
