import { Helmet } from 'react-helmet-async';
import Footer from '../../../../components/Footer';

import { Grid, Container } from '@mui/material';

import ProfileCover from './ProfileCover';
import RecentActivity from './RecentActivity';
import MyCards from './MyCards';
import Addresses from './Addresses';

function ManagementUserProfile() {
  const currentUser = JSON.parse(localStorage.getItem('user') || '{}');
  const user = {
    savedCards: 0,
    name: currentUser.firstName + ' ' + currentUser.lastName,
    coverImg: '/static/images/avatars/avatar1.jpg',
    avatar: '/static/images/avatars/avatar1.jpg',
    description: currentUser.profile,
    jobtitle: currentUser.role,
    location: 'San Antonio, TX',
    followers: '467'
  };

  return (
    <>
      <Helmet>
        <title>User Details - Management</title>
      </Helmet>
      <Container sx={{ mt: 3 }} maxWidth="lg">
        <Grid
          container
          direction="row"
          justifyContent="center"
          alignItems="stretch"
          spacing={3}
        >
          <Grid item xs={12} md={8}>
            <ProfileCover user={user} />
          </Grid>
          <Grid item xs={12} md={4}>
            <RecentActivity />
          </Grid>
          <Grid item xs={12} md={7}>
            <MyCards />
          </Grid>
          <Grid item xs={12} md={5}>
            <Addresses />
          </Grid>
        </Grid>
      </Container>
      <Footer />
    </>
  );
}

export default ManagementUserProfile;
