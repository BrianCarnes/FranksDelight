import { Box, Button, Container, Grid, Typography } from '@mui/material';
import { styled } from '@mui/material/styles';
import Logo from '../../../components/LogoSign'
import { useEffect, useState } from 'react'
import Google from '../../../../google_credentials.json'
import { GoogleLogin } from 'react-google-login';
import { gapi } from 'gapi-script';
import CRUD from '../../../hooks/CRUDService'
import { Link, redirect } from 'react-router-dom'

const TypographyH1 = styled(Typography)(
  ({ theme }) => `
    font-size: ${theme.typography.pxToRem(50)};
`
);

const TypographyH2 = styled(Typography)(
  ({ theme }) => `
    font-size: ${theme.typography.pxToRem(17)};
`
);

const LabelWrapper = styled(Box)(
  ({ theme }) => `
    background-color: ${theme.colors.success.main};
    color: ${theme.palette.success.contrastText};
    font-weight: bold;
    border-radius: 30px;
    text-transform: uppercase;
    display: inline-block;
    font-size: ${theme.typography.pxToRem(11)};
    padding: ${theme.spacing(0.5)} ${theme.spacing(1.5)};
    margin-bottom: ${theme.spacing(2)};
`
);

const MuiAvatar = styled(Box)(
  ({ theme }) => `
    width: ${theme.spacing(8)};
    height: ${theme.spacing(8)};
    border-radius: ${theme.general.borderRadius};
    background-color: #e5f7ff;
    flex-shrink: 0;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0 auto ${theme.spacing(2)};

    img {
      width: 60%;
      height: 60%;
      display: block;
    }
`
);

const TsAvatar = styled(Box)(
  ({ theme }) => `
    width: ${theme.spacing(8)};
    height: ${theme.spacing(8)};
    border-radius: ${theme.general.borderRadius};
    background-color: #dfebf6;
    flex-shrink: 0;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0 auto ${theme.spacing(2)};

    img {
      width: 60%;
      height: 60%;
      display: block;
    }
`
);

function Hero() {
  const userGet = CRUD.getAll('/users');
  const [ profile, setProfile ] = useState([]);
  useEffect(() => {
    const initClient = () => {
      gapi.client.init({
        clientId: Google.web.client_id,
        scope: 'profile'
      });
    };
    gapi.load('client:auth2', initClient);
  });

  // Sets the Google User object to state
  // Redirects to the dashboard
  // And sends the user to the backend if they are not already in the database
  const onSuccess = (res: any) => {
    console.log('success:', res);
    userGet.then((data) => {
      // Check if the user is already in the database
      if (data.Data.filter((user: any) => user.email === res.profileObj.email).length === 0) {
        setProfile(data.Data.filter((user: any) => user.email === res.profileObj.email));
        console.log("Update User!");
        CRUD.update('/users/update', {
          active: true,
          first_name: res.profileObj.givenName,
          last_name: res.profileObj.familyName
        })
          .then(r => console.log(r));
        redirect('/dashboards/admin');
      } else {
          if (res.profileObj.givenName !== null) {
            console.log("Create User!");
            CRUD.create('/users/create', {
              email: res.profileObj.email,
              firstName: res.profileObj.givenName,
              lastName: res.profileObj.familyName,
            }).then(r => {
              console.log(r);
              setProfile(r.Data);
              redirect('/dashboards/admin');
            })
          } else {
            console.log("Create User failed! No name provided!");
          }
        }
    });
  };

  const onFailure = (err: any) => {
    console.log('failed:', err);
    return redirect('/');
  };

  return (
    <Container maxWidth="lg" sx={{ textAlign: 'center' }}>
      <Grid
        spacing={{ xs: 6, md: 10 }}
        justifyContent="center"
        alignItems="center"
        container
      >
        <Grid item md={10} lg={8} mx="auto">
          <Box display="flex" justifyContent="center"  py={0} margin={1} padding={1}>
            <Logo />
          </Box>
          <TypographyH1 sx={{ mb: 2 }} variant="h1">
            Franks Delight
          </TypographyH1>
          <GoogleLogin
            clientId={Google.web.client_id}
            buttonText="Sign in with Google"
            onSuccess={onSuccess}
            onFailure={onFailure}
            cookiePolicy={'single_host_origin'}
            isSignedIn={true}
          />
          {/* <Button */}
          {/*   component={RouterLink} */}
          {/*   to="/dashboards/admin" */}
          {/*   size="large" */}
          {/*   variant="contained" */}
          {/* > */}
          {/*   Browse Live Preview */}
          {/* </Button> */}
          <Grid container spacing={3} mt={5}>
            <Grid item md={6}>
              <MuiAvatar>
                <img
                  src="/static/images/logo/material-ui.svg"
                  alt="Material-UI"
                />
              </MuiAvatar>
              <Typography variant="h4">
                <Box sx={{ pb: 2 }}>
                  <b>Powered by MUI (Material-UI)</b>
                </Box>
                <Typography component="span" variant="subtitle2">
                  A simple and customizable component library to build faster,
                  beautiful, and accessible React apps.
                </Typography>
              </Typography>
            </Grid>
            <Grid item md={6}>
              <TsAvatar>
                <img
                  src="/static/images/logo/typescript.svg"
                  alt="Typescript"
                />
              </TsAvatar>
              <Typography variant="h4">
                <Box sx={{ pb: 2 }}>
                  <b>Built with Typescript</b>
                </Box>
                <Typography component="span" variant="subtitle2">
                  Franks Delight features a modern technology stack and is
                  built with React + Typescript.
                </Typography>
              </Typography>
            </Grid>
          </Grid>
        </Grid>
      </Grid>
    </Container>
  );
}

export default Hero;
