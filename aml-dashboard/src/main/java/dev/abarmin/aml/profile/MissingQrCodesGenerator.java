package dev.abarmin.aml.profile;

import dev.abarmin.aml.QrCodeService;
import dev.abarmin.aml.config.AppConfiguration;
import dev.abarmin.aml.file.FileSaveRequest;
import dev.abarmin.aml.file.FileSaveResponse;
import dev.abarmin.aml.file.FileService;
import dev.abarmin.aml.registration.domain.Profile;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.ProfileRepository;
import dev.abarmin.aml.registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.glxn.qrgen.core.image.ImageType;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MissingQrCodesGenerator implements ApplicationRunner {
  private final JdbcTemplate jdbcTemplate;
  private final AppConfiguration configuration;
  private final ProfileRepository profileRepository;
  private final UserRepository userRepository;
  private final QrCodeService qrCodeService;
  private final FileService fileService;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    final List<ProfileAndUser> profilesWithoutCode = jdbcTemplate.query("select * from user_profiles up where up.profile_qr is null", new RowMapper<>() {
      @Override
      public ProfileAndUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ProfileAndUser(
          rs.getLong("id"),
          rs.getLong("user_id")
        );
      }
    });

    if (profilesWithoutCode.size() > 0) {
      log.warn("Profiles without QR codes: {}", profilesWithoutCode.size());
    }

    profilesWithoutCode.forEach(this::process);
  }

  private void process(ProfileAndUser record) {
    final Profile profile = profileRepository.findById(record.profileId()).orElseThrow();
    final User user = userRepository.findById(record.userId()).orElseThrow();

    final String publicLink = configuration.getBaseUrl() + "/l/" + profile.link();
    final QrCodeService.Request request = new QrCodeService.Request(
      publicLink,
      250,
      250,
      ImageType.PNG
    );
    final ByteArrayOutputStream qrCodeContent = qrCodeService.generate(request);
    final FileSaveRequest saveRequest = new FileSaveRequest(
      user,
      "qr-code-" + user.id() + ".png",
      new ByteArrayInputStream(qrCodeContent.toByteArray())
    );
    final FileSaveResponse saveResponse = fileService.save(saveRequest);
    final Profile withQrCode = profile.withQrCode(saveResponse.fileId());

    profileRepository.save(withQrCode);
  }

  record ProfileAndUser(long profileId, long userId) {

  }
}
