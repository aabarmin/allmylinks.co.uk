import { Col, Container, Row } from 'react-bootstrap';
import { BoxArrowUpRight } from 'react-bootstrap-icons';
import type { DashboardProfile } from './model/DashboardProfile';

interface Props {
    profile: DashboardProfile
}

export default function UserLinkPreview({ profile }: Props) {
    const profileLink = profile.profileLink;

    return (
        <Container>
            <Row>
                <Col>
                    <a href={profileLink} target="_blank">
                        <BoxArrowUpRight />
                    </a>
                    &nbsp;
                    <a href={profileLink} target="_blank">
                        {profileLink}
                    </a>
                </Col>
            </Row>
        </Container>
    );
}