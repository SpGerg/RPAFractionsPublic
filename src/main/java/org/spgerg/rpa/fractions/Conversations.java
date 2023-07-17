package org.spgerg.rpa.fractions;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spgerg.rpa.fractions.utils.PlayerAdsUtils;
import org.spgerg.rpa.fractions.utils.Utils;

public class Conversations {

    public static StringPrompt adEdit = new StringPrompt() {
        @NotNull
        @Override
        public String getPromptText(@NotNull ConversationContext context) {
            return "Введите новое сообщение: ";
        }

        @Nullable
        @Override
        public Prompt acceptInput(@NotNull ConversationContext context, @Nullable String input) {
            String id = (String) context.getSessionData("id");
            String edited = (String) context.getSessionData("edited");

            for (int i = 0;i < Utils.advertises.size();i++) {
                PlayerAdsUtils adsUtils = Utils.advertises.get(i);

                if (adsUtils.id.equals(Integer.parseInt(id))) {
                    adsUtils.message = input;
                    adsUtils.edited = edited;
                    Utils.advertises.set(i, adsUtils);
                }
            }

            return Prompt.END_OF_CONVERSATION;
        }
    };
}
