import { useEffect, useState } from 'react';
import PlayerList from './components/player_list/PlayerList';
import Flag from './components/flag/Flag';
import Data from './components/data/Data';
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import { getAllCountries, getCountryById } from './services/CountryService';
import type { Country } from './types/country/Country';
import { getAllPlayers } from './services/PlayerService';
import type { Player } from './types/player/Player';
import type { Club } from './types/team/Club';
import { getAllClubs, getClubById } from './services/ClubService';
import { extractFullSquad } from './utils/TeamUtils';
import { getAllLeagues } from './services/LeagueService';
import type { League } from './types/competition/League';

function App() {
  const [countries, setCountries] = useState<Country[]>([]);
  const [clubs, setClubs] = useState<Club[]>([]);
  const [club, setClub] = useState<Club | null>(null);
  const [players, setPlayers] = useState<Player[]>([]);
  const [country, setCountry] = useState<Country | null>(null);
  const [leagues, setLeagues] = useState<League[]>([]);

  useEffect(() => {
    //loadPlayers();
    //loadCountries();
    //loadCountry('SRB');
    //loadClubs();
    //loadClub('realmadrid');
    loadLeagues();
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
    {/* {country !== null ? <Flag key={country.id} country={country}></Flag> : null} */}
    <Data></Data>
    {/* {players.length ? <div style={{width:'800px', marginLeft: '20px'}}><PlayerList players={players} currentYear={new Date().getFullYear()} variant='preview' includeHeader={true}></PlayerList></div> : null} */}
      {/* <Router>
        <Navbar></Navbar>
        <Routes>
          <Route path='/' element={<Home/>}></Route>
          <Route path='/lineup' element={<Lineup/>}></Route>
        </Routes>
      </Router> */}
    </>
  )
}

export default App