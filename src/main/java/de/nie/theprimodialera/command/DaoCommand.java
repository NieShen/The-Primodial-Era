package de.nie.theprimodialera.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class DaoCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("learnDao")
                .then(Commands.argument("daoName", StringArgumentType.string())
                        .executes(context -> {
                            String daoName = StringArgumentType.getString(context, "daoName");
                            // Lookup dao and call learnDao
                            // Implement your logic here
                            return Command.SINGLE_SUCCESS; // Placeholder for actual implementation
                        })));

        dispatcher.register(Commands.literal("gainExperience")
                .then(Commands.argument("daoName", StringArgumentType.string())
                        .then(Commands.argument("amount", IntegerArgumentType.integer(0)) // Ensure amount is >= 0
                                .executes(context -> {
                                    String daoName = StringArgumentType.getString(context, "daoName");
                                    int amount = IntegerArgumentType.getInteger(context, "amount");
                                    // Lookup dao and call gainExperience
                                    // Implement your logic here
                                    return Command.SINGLE_SUCCESS; // Placeholder for actual implementation
                                }))));
    }
}
