package dev.abarmin.aml.dashboard.block.avatar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.abarmin.aml.dashboard.domain.BlockProps;
import dev.abarmin.aml.file.FileId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvatarBlockProps implements BlockProps {
  public static final FileId DEFAULT_AVATAR = FileId.resource("/img/avatar_placeholder.png");

  private FileId avatarId;
  private FileId backgroundId;

  @JsonIgnore
  public Map<String, String> getAvatarStyle() {
    if (Objects.nonNull(backgroundId)) {
      return Map.of(
        "margin-top", "-100px",
        "border", "5px solid #ffffff"
      );
    }
    return Map.of();
  }
}
