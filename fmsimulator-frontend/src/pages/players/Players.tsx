import { useEffect, useState } from 'react';
import Navbar from '../../components/navbar/Navbar';
import PlayerList from '../../components/player_list/PlayerList';
import { mainNavbarItems } from '../../constants/NavbarItems';
import pageStyles from './../Page.module.css';
import styles from './Players.module.css';
import type {Player} from '../../types/models/player/Player';
import { getAllPlayers, getAllPlayersByPage } from '../../services/PlayerService';
import type { PlayersPageResponse } from '../../types/responses/Responses';

const Players = () => {
    const [players, setPlayers] = useState<Player[]>([]);
    const [page, setPage] = useState<number>(0);
    const [totalPages, setTotalPages] = useState<number>(1);
    const [totalPlayers, setTotalPlayers] = useState<number>(0);
    const [lastSortingKey, setLastSortingKey] = useState<keyof Player | undefined>(undefined);
    const [lastSortingOrder, setLastSortingOrder] = useState<"asc" | "desc">("asc");
    const [lastSortingOrientation, setLastSortingOrientation] = useState<string>("");
    const playerLimitPerPage = 26; 

    const loadData = async (page: number, sortingKey?: keyof Player, sortingOrder?: "asc" | "desc", sortingOrientation?: string) => {
        try {
            const data = await getAllPlayersByPage(page, playerLimitPerPage, sortingKey, sortingOrder, sortingOrientation);
            setPlayers(data.players);
            setTotalPlayers(data.totalPlayers);
            setPage(data.page);
            setTotalPages(data.totalPages);
            if(sortingKey) setLastSortingKey(sortingKey);
            if(sortingOrder) setLastSortingOrder(sortingOrder);
            if(sortingOrientation) setLastSortingOrientation(sortingOrientation);
        } catch (error) {
            console.log("Failed to retrieve data in Players component.");
        }
    }

    useEffect(() => {
        loadData(0);
    }, []);
    
    const handleFirstButtonClicked = () => {
        loadData(0, lastSortingKey, lastSortingOrder, lastSortingOrientation);
    }

    const handlePreviousButtonClicked = () => {
        loadData(page-1, lastSortingKey, lastSortingOrder, lastSortingOrientation);
    }

    const handleNextButtonClicked = () => {
        loadData(page+1, lastSortingKey, lastSortingOrder, lastSortingOrientation);
    }

    const handleLastButtonClicked = () => {
        loadData(totalPages-1, lastSortingKey, lastSortingOrder, lastSortingOrientation);
    }

    const handleHeaderClicked = (sortingKey: keyof Player, sortingOrientation?: string) => {
        if(lastSortingKey === sortingKey && sortingKey !== 'positions') {
            if(lastSortingOrder === 'desc') {
                loadData(0, sortingKey, "asc", sortingOrientation);
            }else{
                loadData(0, sortingKey, "desc", sortingOrientation);
            }
        }else{
            loadData(0, sortingKey, "asc", sortingOrientation);
        }
    }

    return (
        <div className={pageStyles.root}>
            <div className={pageStyles.navbar}><Navbar items={mainNavbarItems}></Navbar></div>
            <div className={pageStyles.content}>
                <div className={styles.filter_section}>FILTER</div>
                <div className={styles.data_section}>
                    <PlayerList players={players} currentYear={2025} onHeaderClicked={handleHeaderClicked}></PlayerList>
                <div className={styles.footer}>
                    <div className={styles.footer_buttons}>
                         <button className={page < 1 ? styles.button_disabled : styles.button_enabled} disabled={page < 1} onClick={() => handleFirstButtonClicked()}>First</button>
                        <button className={page < 1 ? styles.button_disabled : styles.button_enabled} disabled={page < 1} onClick={() => handlePreviousButtonClicked()}>{"< Previous"}</button>
                        <button className={page >= (totalPages-1) ? styles.button_disabled : styles.button_enabled} disabled={page >= (totalPages-1)} onClick={handleNextButtonClicked}>{"Next >"}</button>
                        <button className={page >= (totalPages-1) ? styles.button_disabled : styles.button_enabled} disabled={page >= (totalPages-1)} onClick={() => handleLastButtonClicked()}>{`Last [${totalPages}]`}</button>
                    </div>
                    <div className={styles.footer_info}>Showing&nbsp;
                        <span>{page+1}</span>&nbsp;out of&nbsp;<span>{totalPages}&nbsp;</span>pages&nbsp;&nbsp;{"("}<span>{totalPlayers}</span>
                    &nbsp;total players found{")."}</div>
                </div>
                </div>
            </div>
        </div>
    );
}
export default Players;