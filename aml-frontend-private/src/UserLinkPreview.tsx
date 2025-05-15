import { Container, Row, Col } from 'react-bootstrap';
import type { DashboardProfile } from './model/DashboardProfile';
import { BoxArrowUpRight } from 'react-bootstrap-icons';

interface Props {
    profile: DashboardProfile
}

export default function UserLinkPreview({ profile }: Props) {
    const profileLink = profile.profileLink;

    return (
        <Container>
            <Row>
                <Col>
                    <a
                        className="icon-link"
                        href={profileLink}
                        target="_blank">

                        <BoxArrowUpRight />
                    </a>
                    <a href={profileLink} target="_blank">
                        {profileLink}
                    </a>
                </Col>
            </Row>
        </Container>
    );
}