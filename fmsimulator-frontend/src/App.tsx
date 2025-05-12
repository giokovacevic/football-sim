import React from 'react';
import logo from './logo.svg';
import './App.css';
import { useState, useEffect } from 'react';
import LeagueMember from './components/LeagueMember';
import LeagueTable from './components/LeagueTable';
import DropdownMenu from './components/dropdown_menu/DropdownMenu';
import Pitch from './components/pitch/Pitch';
import Lineup from './components/lineup/Lineup';
import Roster from './components/roster/Roster';
import Test from './practice_components/Test';

// 

interface DragData {
  mode: string;
  data: string;
}


function App() {
  const [info, setInfo] = useState<any[]>([]);
  const [formations, setFormations] = useState<any>([]);
  const [currentFormation, setCurrentFormation] = useState<any>(null);
  const [countries, setCountries] = useState<any>([]);
  const [currentCountry, setCurrentCountry] = useState<any>(null);
  const [myClub, setMyClub] = useState<any>(null);
  const [dragData, setDragData] = useState<DragData>({mode: "", data: ""});


  useEffect(()=>{
    fetchFormations();
    //fetchCountries();
    fetchMyClub();
  }, []);

  const fetchMyClub = async () => {
    try{
      const response = await fetch('http://localhost:8080/api/football/myclub');
      const data = await response.json();
      //console.log(data);
      setMyClub(data);
      console.log(data);
    }catch(error) {
      console.error("Error in fetching my Club");
    }
  }

  const fetchFormations = async () => {
    try{
      const response = await fetch('http://localhost:8080/api/football/formations-all');
      const data = await response.json();
      setFormations(data);
      setCurrentFormation(data[0]);
    }catch(error) {
      console.error("Error in fetching all Formations");
    }
  }

  const fetchCountries = async () => {
    try{
      const response = await fetch('http://localhost:8080/api/football/countries-all');
      const data = await response.json();
      setCountries(data);
      setCurrentCountry(data[0]);
    }catch(error) {
      console.error("Error in fetching Countries");
    }
  }

  const fetchInitialData = async () => {
    try{
      const response = await fetch('http://localhost:8080/api/football/test');
      const data = await response.json();
      setInfo(data);
    }catch(error) {
      console.error("Error in fetching data");
    }
  }


  const simulateMatchDay = async () => {
    try{
      await fetch('http://localhost:8080/api/football/simulate-matchday', {method: 'POST'});
      fetchInitialData();
    }catch(error){
      console.error("Error in function simulateDay()");
    }
  }

  function simulateDay() {
    simulateMatchDay();
  }

  const postFormationChange = async (formationId:string) => {
    try{
      await fetch('http://localhost:8080/api/football/formation-change', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formationId),
      });
      fetchMyClub();
    }catch(error){
      console.error("Error in function POST: formation-change");
    }
  }

  const postSubstitute = async (dataMode:string, dataIn:string, dataOut:string) => {
    try{
      await fetch('http://localhost:8080/api/football/substitute', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({mode:dataMode, in: dataIn, out: dataOut}),
      });
      fetchMyClub();
    }catch(error){
      console.error("Error in function POST: substitute");
    }
  }

  const postSaveLineup = async () => {
    try{
      await fetch('http://localhost:8080/api/football/save-lineup', {method: 'POST'});
      fetchMyClub();
    }catch(error){
      console.log(" Error in postSaveLineup:  " + error);
    }
  }

  const changeFormation = (formation:any) => {
    postFormationChange(formation.id);
    setCurrentFormation(formation);
  }
  

  function handleOnDrag(e: React.DragEvent, country:any) {
    const data = JSON.stringify(country);
    e.dataTransfer.setData('application/json', data);
    const loadedData = e.dataTransfer.getData('application/json');
  }

 function handleOnDrop(e: React.DragEvent) {
    e.preventDefault();
    const data = e.dataTransfer.getData("application/json");
    const country = JSON.parse(data);
    console.log("Country name:  ", country);
    
    //setItem(<div className='div-new' onClick={() => setItem(<div></div>)}>{widgetType}</div>);
  }

  function handleDragOver(e: React.DragEvent) {
    e.preventDefault();
  }
//<div style={{marginTop: '30px',height: '26px', backgroundColor: 'grey', width: '300px'}} draggable onDragStart={(e) => handleOnDrag(e, formations[1])}>PLAYER CARD</div>
  function sub(data:string) {
    console.log(data);
  }



  return (
    <>
      <Test message='test'></Test>
    </>
  );

  /* The football code
  
  <div style={{width: '720px' ,float: 'left', marginLeft: '65px'}}>
      {myClub ? (<Roster club={myClub} setDragData={setDragData} substitute={postSubstitute}></Roster>)
        : (<div>Loading...</div>)}
      </div>
      <div style={{marginLeft: '800px', marginTop: '0px'}}>
        {myClub ? (<Lineup myClub={myClub} formations={formations} currentFormation={myClub.roster.lineup.formationDTO} changeFormation={changeFormation} setDragData={setDragData} dragData={dragData} substitute={postSubstitute} save={postSaveLineup} textKey="id" iconKey="_default" iconPath="./assets/formation_default_icon.png"></Lineup>)
        : (<div>Loading...</div>)}
      </div>

   */

  /*


  /*return (
    <>
        {myClub ? (
          <>
            <RosterMemberTest player={myClub.roster.bench[0]}></RosterMemberTest>
            <LineupMemberTest player={myClub.roster.lineup.starters["CF"].player} sub={sub}></LineupMemberTest>
          </>
        )
          : (<div>Loading...</div>)}
    </>
  );*/

    /*return (
      <>
        <div style={{width: '720px' ,float: 'left', marginLeft: '65px'}}>
        {myClub ? (<Roster club={myClub}></Roster>)
          : (<div>Loading...</div>)}
        </div>
        <div style={{marginLeft: '800px', marginTop: '0px'}}>
          {myClub ? (<Lineup myClub={myClub} formations={formations} currentFormation={currentFormation} changeFormation={changeFormation} textKey="id" iconKey="_default" iconPath="./assets/formation_default_icon.png"></Lineup>)
          : (<div>Loading...</div>)}
        </div>
      </>
    );*/
}

export default App;
