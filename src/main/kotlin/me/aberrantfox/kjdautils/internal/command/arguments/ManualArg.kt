package me.aberrantfox.kjdautils.internal.command.arguments

import me.aberrantfox.kjdautils.api.dsl.CommandEvent
import me.aberrantfox.kjdautils.internal.command.ArgumentResult
import me.aberrantfox.kjdautils.internal.command.ArgumentType
import me.aberrantfox.kjdautils.internal.command.ConsumptionType

object Manual : ArgumentType {
    override val examples = arrayListOf("None-specified")
    override val name = "Unknown"
    override val consumptionType = ConsumptionType.All
    override fun convert(arg: String, args: List<String>, event: CommandEvent) = ArgumentResult.Multiple(args, args)
}