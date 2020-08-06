package me.jakejmattson.discordkt.api.arguments

import me.jakejmattson.discordkt.api.dsl.arguments.*
import me.jakejmattson.discordkt.api.dsl.command.CommandEvent
import me.jakejmattson.discordkt.api.extensions.stdlib.trimToID
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Accepts a Discord TextChannel entity as an ID or mention.
 *
 * @param allowsGlobal Whether or not this entity can be retrieved from outside this guild.
 */
open class TextChannelArg(override val name: String = "Text Channel", private val allowsGlobal: Boolean = false) : ArgumentType<TextChannel>() {
    /**
     * Accepts a Discord TextChannel entity as an ID or mention from within this guild.
     */
    companion object : TextChannelArg()

    override fun convert(arg: String, args: List<String>, event: CommandEvent<*>): ArgumentResult<TextChannel> {
        val channel = event.discord.retrieveEntity {
            it.getTextChannelById(arg.trimToID())
        } ?: return Error("Not found")

        if (!allowsGlobal && channel.guild.id != event.guild?.id)
            return Error("Must be from this guild.")

        return Success(channel)
    }

    override fun generateExamples(event: CommandEvent<*>) = listOf(event.channel.id)

    override fun formatData(data: TextChannel) = "#${data.name}"
}