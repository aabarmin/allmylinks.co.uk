package dev.abarmin.aml.dashboard.rest.converter;

import dev.abarmin.aml.dashboard.block.social.SocialNetworkLink;
import dev.abarmin.aml.dashboard.domain.AvatarBlockProps;
import dev.abarmin.aml.dashboard.domain.BlockProps;
import dev.abarmin.aml.dashboard.domain.HeaderBlockProps;
import dev.abarmin.aml.dashboard.domain.LinkButtonBlockProps;
import dev.abarmin.aml.dashboard.domain.SocialNetworksBlockProps;
import dev.abarmin.aml.dashboard.rest.response.AvatarBlockPropsResponse;
import dev.abarmin.aml.dashboard.rest.response.BlockPropsResponse;
import dev.abarmin.aml.dashboard.rest.response.FileResponse;
import dev.abarmin.aml.dashboard.rest.response.HeaderBlockPropsResponse;
import dev.abarmin.aml.dashboard.rest.response.LinkButtonBlockPropsResponse;
import dev.abarmin.aml.dashboard.rest.response.SocialNetworkLinkResponse;
import dev.abarmin.aml.dashboard.rest.response.SocialNetworksBlockPropsResponse;
import dev.abarmin.aml.file.FileId;
import dev.abarmin.aml.file.Storage;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BlockPropsConverter {

  private final FileResponseConverter fileConverter;

  public BlockPropsResponse convert(BlockProps props) {
    return switch(props) {
      case HeaderBlockProps headerBlockProps -> convert(headerBlockProps);
      case AvatarBlockProps avatarBlockProps -> convert(avatarBlockProps);
      case LinkButtonBlockProps linkButtonProps -> convert(linkButtonProps);
      case SocialNetworksBlockProps networkProps -> convert(networkProps);
    };
  }

  public BlockProps convert(BlockPropsResponse props) {
    return switch(props) {
      case HeaderBlockPropsResponse headerBlockPropsResponse -> convert(headerBlockPropsResponse);
      case AvatarBlockPropsResponse avatarBlockPropsResponse -> convert(avatarBlockPropsResponse);
      case LinkButtonBlockPropsResponse linkButtonPropsResponse -> convert(linkButtonPropsResponse);
      case SocialNetworksBlockPropsResponse socialNetworksBlockPropsResponse -> convert(socialNetworksBlockPropsResponse);
    };
  }

  private HeaderBlockProps convert(HeaderBlockPropsResponse props) {
    return new HeaderBlockProps(
      props.text(),
      props.level(),
      props.alignment()
    );
  }

  private AvatarBlockProps convert(AvatarBlockPropsResponse props) {
    return new AvatarBlockProps(
      convert(props.avatar()),
      convert(props.background()),
      props.showShareButton()
    );
  }

  private LinkButtonBlockProps convert(LinkButtonBlockPropsResponse props) {
    return new LinkButtonBlockProps(
      props.text(),
      props.link(),
      props.size(),
      props.color()
    );
  }

  private SocialNetworksBlockProps convert(SocialNetworksBlockPropsResponse props) {
    return new SocialNetworksBlockProps(
      props.links().stream()
        .map(this::convert)
        .toList()
    );
  }

  private HeaderBlockPropsResponse convert(HeaderBlockProps props) {
    return new HeaderBlockPropsResponse(
      props.getText(),
      props.getLevel(),
      props.getAlignment()
    );
  }

  private AvatarBlockPropsResponse convert(AvatarBlockProps props) {
    return new AvatarBlockPropsResponse(
      convert(props.getAvatarId()),
      convert(props.getBackgroundId()),
      props.isShowShareButton()
    );
  }

  private LinkButtonBlockPropsResponse convert(LinkButtonBlockProps props) {
    return new LinkButtonBlockPropsResponse(
      props.getText(),
      props.getLink(),
      props.getSize(),
      props.getColor()
    );
  }

  private SocialNetworksBlockPropsResponse convert(SocialNetworksBlockProps props) {
    return new SocialNetworksBlockPropsResponse(
      props.getLinks().stream()
        .map(this::convert)
        .toList()
    );
  }

  private SocialNetworkLinkResponse convert(SocialNetworkLink link) {
    return new SocialNetworkLinkResponse(
      link.getUrl(),
      link.getNetwork()
    );
  }

  private SocialNetworkLink convert(SocialNetworkLinkResponse link) {
    return new SocialNetworkLink(
      link.network(),
      link.url()
    );
  }

  private FileResponse convert(@Nullable FileId fileId) {
    return Optional.ofNullable(fileId)
      .map(fileConverter::convert)
      .orElse(null);
  }

  private FileId convert(@Nullable FileResponse file) {
    return Optional.ofNullable(file)
      .map(f -> new FileId(Storage.valueOf(f.storage()), f.filePath()))
      .orElse(null);
  }

}
