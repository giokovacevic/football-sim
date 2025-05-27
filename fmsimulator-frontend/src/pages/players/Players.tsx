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
    const playerLimitPerPage = 26;

    const loadData = async (page: number) => {
        try {
            const data = await getAllPlayersByPage(page, playerLimitPerPage);
            setPlayers(data.players);
            setTotalPlayers(data.totalPlayers);
            setTotalPages(data.totalPages);
        } catch (error) {
            console.log("Failed to retrieve data in Players component.");
        }
    }

    useEffect(() => {
        loadData(page);
    }, [page]);

    const handlePreviousButtonClicked = () => {
        setPage(page-1);
    }

    const handleNextButtonClicked = () => {
        setPage(page+1);
    }

    const handleFirstButtonClicked = () => {
        setPage(0);
    }

    const handleLastButtonClicked = () => {
        setPage(totalPages-1);
    }

    return (
        <div className={pageStyles.root}>
            <div className={pageStyles.navbar}><Navbar items={mainNavbarItems}></Navbar></div>
            <div className={pageStyles.content}>
                <div className={styles.filter_section}>FILTER</div>
                <div className={styles.data_section}>
                    <PlayerList players={players} currentYear={2025}></PlayerList>
                <div className={styles.footer}>
                    <div className={styles.footer_buttons}>
                        <button className={page < 1 ? styles.button_disabled : styles.button_enabled} disabled={page < 1} onClick={() => handlePreviousButtonClicked()}>{"< Previous"}</button>
                        <button className={page >= (totalPages-1) ? styles.button_disabled : styles.button_enabled} disabled={page >= (totalPages-1)} onClick={handleNextButtonClicked}>{"Next >"}</button>
                        <button className={page < 1 ? styles.button_disabled : styles.button_enabled} disabled={page < 1} onClick={() => handleFirstButtonClicked()}>First</button>
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