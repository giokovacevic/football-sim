import { useEffect, useState } from 'react';
import PlayerList from './components/old_player_list/PlayerList';
import Flag from './components/flag/Flag';
import Data from './components/data/Data';
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import { getAllCountries, getCountryById } from './services/CountryService';
import type { Country } from './types/models/country/Country';
import { getAllPlayers } from './services/PlayerService';
import type { Player } from './types/models/player/Player';
import type { Club as IClub } from './types/models/team/Club';
import { getAllClubs, getClubById } from './services/ClubService';
import { extractFullSquad } from './utils/TeamUtils';
import { getAllLeagues } from './services/LeagueService';
import type { League } from './types/models/competition/League';
import Pitch from './components/pitch/Pitch';
import Navbar from './components/navbar/Navbar';
import Home from './pages/home/Home';
import Leagues from './pages/leagues/Leagues';
import Clubs from './pages/clubs/Clubs';
import Players from './pages/players/Players';
import { AuthContext, AuthProvider } from './contexts/AuthContext';
import ProtectedRoute from './components/auth/ProtectedRoute';
import Club from './pages/club/Club';
import ClubWrapper from './pages/club/ClubWrapper';

function App() {
  const [countries, setCountries] = useState<Country[]>([]);
  const [clubs, setClubs] = useState<IClub[]>([]);
  const [club, setClub] = useState<IClub | null>(null);
  const [players, setPlayers] = useState<Player[]>([]);
  const [country, setCountry] = useState<Country | null>(null);
  const [leagues, setLeagues] = useState<League[]>([]);

  useEffect(() => {
    //loadPlayers();
    //loadCountries();
    //loadCountry('SRB');
    //loadClubs();
    loadClub('parma');
    //loadLeagues();
  }, []);

  const loadCountries = async () => {
    try {
      const data = await getAllCountries();
      console.log(data);
      setCountries(data);
    } catch (error) {
      console.log(`No countries found.`);
      setCountries([]);
    }
  }

  const loadCountry = async (countryId:string) => {
    try {
      const data = await getCountryById(countryId);
      setCountry(data);
    } catch (error) {
      console.log(`No country found with such id: ${countryId}`);
      setCountry(null);
    }
  }

  const loadPlayers = async () => {
    try {
      const data = await getAllPlayers();
      setPlayers(data);
    } catch (error) {
      setPlayers([]);
    }
  }

  const loadClubs = async () => {
    try {
      const data = await getAllClubs();
      setClubs(data);
      console.log(data);
    } catch (error) {
      setClubs([]);
    }
  }

  const loadClub = async (clubId:string) => {
    try {
      const data = await getClubById(clubId);
      setClub(data);
    } catch (error) {
      setClub(null);
    }
  }

  const loadLeagues = async () => {
    try {
      const data = await getAllLeagues();
      setLeagues(data);
      console.log(data);
    } catch (error) {
      setLeagues([]);
      console.log('Fail in loading Leagues');
    }
  }

  

  return (
    <>
    {/* {club ? <Pitch key={club.id} team={club} kits={club.preferredJersey}></Pitch> : null} */}
    
    {/* {country !== null ? <Flag key={country.id} country={country}></Flag> : null} */}
    {/* <Data></Data> */}
    {/* {players.length ? <div style={{width:'800px', marginLeft: '20px'}}><PlayerList players={players} currentYear={new Date().getFullYear()} variant='preview' includeHeader={true}></PlayerList></div> : null} */}
      {/* <Router>
        <Navbar></Navbar>
        <Routes>
          <Route path='/' element={<Home/>}></Route>
          <Route path='/lineup' element={<Lineup/>}></Route>
        </Routes>
      </Router> */}
      <AuthProvider>
      <Router>
        <Routes>
            <Route path='/' element={<Home/>}></Route>
            <Route path='/leagues'  element={<ProtectedRoute><Leagues/></ProtectedRoute>}></Route>
            <Route path='/clubs'  element={<Clubs/>}></Route>
            <Route path='/players'  element={<Players/>}></Route>
            <Route path={`/clubs/:clubId`}  element={<ClubWrapper />}></Route>
            <Route path='*'  element={<Home/>}></Route>
        </Routes>
      </Router>
      </AuthProvider>
    </>
  )
}

export default App