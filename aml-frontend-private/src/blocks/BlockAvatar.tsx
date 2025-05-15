import { Modal, Button } from 'react-bootstrap';

interface BlockAvatarProps {
    block: {
        blockProps: {
            backgroundId: string | null;
            showShareButton: boolean;
            avatarId: string;
            getCoverStyle: () => React.CSSProperties;
            getAvatarStyle: () => React.CSSProperties;
        };
        canMoveUp: boolean;
    };
    profile: {
        profileShortLink: string;
        profileQr: string;
        profileLink: string;
        share: {
            onFacebook: string;
            onTwitter: string;
            onLinkedin: string;
        };
    };
}

export default function BlockAvatar({ block, profile }: BlockAvatarProps) {
    const [showModal, setShowModal] = React.useState(false);

    const handleModalOpen = () => setShowModal(true);
    const handleModalClose = () => setShowModal(false);

    const { blockProps } = block;

    return (
        <div className="preview-pane-block-no-padding">
            <div className="row" style={{ marginLeft: 0, marginRight: 0 }}>
                <div
                    className={`col avatar-container ${
                        blockProps.backgroundId == null ? 'avatar-container-no-background' : ''
                    }`}
                >
                    {blockProps.showShareButton && (
                        <div className="avatar-share-button">
                            <Button
                                variant="outline-primary"
                                onClick={handleModalOpen}
                            >
                                <i className="bi bi-share"></i>
                            </Button>
                        </div>
                    )}

                    {blockProps.backgroundId != null && (
                        <div
                            className={`avatar-background ${
                                block.canMoveUp === false ? 'avatar-background-rounded-up' : ''
                            }`}
                            style={blockProps.getCoverStyle()}
                        ></div>
                    )}

                    <div
                        className={`avatar-photo-container ${
                            blockProps.backgroundId == null ? 'avatar-photo-container-no-background' : ''
                        }`}
                    >
                        <img
                            src={blockProps.avatarId}
                            className="avatar-photo rounded-circle"
                            style={blockProps.getAvatarStyle()}
                            height="200"
                            width="200"
                            alt="Avatar"
                        />
                    </div>
                </div>
            </div>

            {/* Share Modal */}
            <Modal show={showModal} onHide={handleModalClose} id="shareModal">
                <Modal.Dialog>
                    <Modal.Header closeButton>
                        <Modal.Title>{profile.profileShortLink}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body className="text-center">
                        <div className="mb-3">
                            <img src={profile.profileQr} alt="QR Code" />
                        </div>
                        <div className="mb-3 d-grid">
                            <Button
                                variant="primary"
                                data-url={profile.profileLink}
                                id="copyToClipboardButton"
                            >
                                <i className="bi bi-copy"></i> Copy page URL
                            </Button>
                        </div>
                        <div className="mb-3">
                            <a
                                href={profile.share.onFacebook}
                                target="_blank"
                                rel="noopener noreferrer"
                                className="btn btn-primary"
                            >
                                <i className="bi bi-facebook"></i>
                            </a>
                            <a
                                href={profile.share.onTwitter}
                                target="_blank"
                                rel="noopener noreferrer"
                                className="btn btn-primary"
                            >
                                <i className="bi bi-twitter"></i>
                            </a>
                            <a
                                href={profile.share.onLinkedin}
                                target="_blank"
                                rel="noopener noreferrer"
                                className="btn btn-primary"
                            >
                                <i className="bi bi-linkedin"></i>
                            </a>
                        </div>
                    </Modal.Body>
                </Modal.Dialog>
            </Modal>
        </div>
    );
}