package discord4j.discordjson.json.gateway;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import discord4j.discordjson.json.ApplicationCommandData;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableApplicationCommandUpdate.class)
@JsonDeserialize(as = ImmutableApplicationCommandUpdate.class)
public interface ApplicationCommandUpdate extends Dispatch {

    static ImmutableApplicationCommandUpdate.Builder builder() {
        return ImmutableApplicationCommandUpdate.builder();
    }

    @JsonProperty("guild_id")
    String guildId();

    @JsonUnwrapped
    ApplicationCommandData command();
}
